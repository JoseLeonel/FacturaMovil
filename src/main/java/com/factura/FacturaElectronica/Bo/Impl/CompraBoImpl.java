package com.factura.FacturaElectronica.Bo.Impl;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.CompraBo;
import com.factura.FacturaElectronica.Dao.ArticuloDao;
import com.factura.FacturaElectronica.Dao.CompraDao;
import com.factura.FacturaElectronica.Dao.DetalleCompraDao;
import com.factura.FacturaElectronica.Dao.InventarioDao;
import com.factura.FacturaElectronica.Dao.KardexDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.Utils;
import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.DetalleCompra;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.web.command.CompraCommand;
import com.factura.FacturaElectronica.web.command.DetalleCompraCommand;
import com.google.gson.Gson;

/**
 * Reglas de negocio para las compras hacia el inventario CompraBoImpl.
 * @author jose.
 * @since 27 may. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("compraBo")
public class CompraBoImpl implements CompraBo {

	@Autowired
	CompraDao				compraDao;
	

	@Autowired
	DetalleCompraDao				detalleCompraDao;

	@Autowired
	ArticuloDao			articuloDao;

	@Autowired
	KardexDao				kardexDao;

	@Autowired
	InventarioDao		inventarioDao;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(Compra compra) {
		compraDao.agregar(compra);

	}

	/**
	 * Se realiza la creacion de la compra con sus detalles de productos listos para ingresar al inventario
	 * @param compraCommand
	 * @throws Exception
	 */
	@Override
	public void crearCompra(CompraCommand compraCommand) throws Exception {
		try {
			Compra compra = compraCommand.getId() == null || compraCommand.getId() == Constantes.ZEROS ?  new Compra():compraDao.findById(compraCommand.getId());
			compra.setConsecutivo(compraCommand.getConsecutivo());
			compra.setEmpresa(compraCommand.getEmpresa());
			compra.setEstado(compraCommand.getEstado());
			compra.setFechaCompra(Utils.pasarADate(compraCommand.getFechaCompra(),"yyyy-MM-dd"));
			compra.setFechaCredito(compraCommand.getFechaCredito() == Constantes.EMPTY?null: Utils.pasarADate(compraCommand.getFechaCredito(),"yyyy-MM-dd"));
			compra.setFormaPago(compraCommand.getFormaPago());
			compra.setNota(compraCommand.getNota());
			compra.setTotalCompra(compraCommand.getTotalCompra());
			compra.setTotalDescuento(compraCommand.getTotalDescuento());
			compra.setTotalImpuesto(compraCommand.getTotalImpuesto());
			compra.setUsuarioCreacion(compraCommand.getUsuarioCreacion());
			compra.setTipoDocumento(compraCommand.getTipoDocumento());
			compra.setProveedor(compraCommand.getProveedor());
			
			compra.setSubTotal(compraCommand.getSubTotal());
			
			if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
				compra.setFechaIngreso(new Date());
				compra.setUpdated_at(new Date());
			}
			
			 if(compra.getId() == 0) {
				 compra.setCreated_at(new Date());
	    	 agregar(compra); 
	     }else {
	    	 modificar(compra);
	     }   
			
			if(compraCommand.getId() !=null ) {
				if(compraCommand.getId() > 0) {
					compraDao.eliminarDetalleComprasPorSP(compra);
				}
			}

			
			JSONObject json = null;
			try {
				json = (JSONObject) new JSONParser().parse(compraCommand.getDetalleCompra());
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleCompra = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleCompra != null) {
				for (int i = 0; i < jsonArrayDetalleCompra.size(); i++) {
					DetalleCompraCommand detalleCompraCommand = gson.fromJson(jsonArrayDetalleCompra.get(i).toString(), DetalleCompraCommand.class);
					Articulo articulo = articuloDao.buscar(detalleCompraCommand.getArticulo_id());
					detalleCompraCommand.setArticulo(articulo);

					DetalleCompra detalleCompra = new DetalleCompra(detalleCompraCommand);
          detalleCompra.setCompra(compra);
          detalleCompra.setCreated_at(new Date());
          detalleCompra.setUpdated_at(new Date());
          //detalleCompraDao.agregar(detalleCompra);
					compra.addDetalleCompra(detalleCompra);
					compraDao.modificar(compra); 
					Inventario inventario = inventarioDao.findByArticuloAndEstado(detalleCompraCommand.getArticulo(), Constantes.ESTADO_ACTIVO);

					aplicarInventario(compra, inventario, detalleCompra, articulo);

				}
			}
    
			

		} catch (Exception e) {
			log.info("** Error  crearCompra: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}

	}

	/**
	 * Aplicar el inventario si estado de la compras es ingresar al inventario
	 * @param compra
	 * @param inventario
	 */
	public void aplicarInventario(Compra compra, Inventario inventario, DetalleCompra detalleCompra, Articulo articulo) {
		if (compra.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
			//No se encuentra en el inventario
			if (inventario == null) {
				inventario = new Inventario();
				inventario.setArticulo(articulo);
				inventario.setCantidad(Constantes.ZEROS_DOUBLE);
				inventario.setMinimo(Constantes.INVENTARIO_MINIMO);
				inventario.setMaximo(Constantes.INVENTARIO_MAXIMO);
				inventario.setCreated_at(new Date());
				inventario.setUpdated_at(new Date());
				inventario.setUsuario(compra.getUsuarioCreacion());
				inventario.setEstado(Constantes.ESTADO_ACTIVO);
				inventarioDao.agregar(inventario);

			}

			String leyenda = Constantes.MOTIVO_INGRESO_INVENTARIO_COMPRA + compra.getProveedor().getNombreCompleto();
			kardexDao.entrada(inventario, detalleCompra.getCantidad(), compra.getNota(), compra.getConsecutivo(), Constantes.KARDEX_TIPO_ENTRADA, leyenda, compra.getUsuarioCreacion());
			articulo.setCosto(articuloDao.costoPromedio(articulo.getCosto(), detalleCompra.getCosto(), inventario.getCantidad(), detalleCompra.getCantidad()));
			articulo.setGananciaPrecioPublico(articuloDao.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), articulo.getPrecioPublico()));
			articulo.setUpdated_at(new Date());
			articulo.setUsuario(compra.getUsuarioCreacion());
			articuloDao.modificar(articulo);
			inventario.setCantidad(inventario.getCantidad()+detalleCompra.getCantidad());
			inventario.setUsuario(compra.getUsuarioCreacion());
			inventario.setUpdated_at(new Date());
			inventarioDao.modificar(inventario);

		}

	}

	/**
	 * Modificar una compra
	 * @see com.factura.FacturaElectronica.Bo.CompraBo#modificar(com.factura.FacturaElectronica.modelo.Compra)
	 */
	@Override
	public void modificar(Compra compra) {
		compraDao.modificar(compra);
	}

	/**
	 * Eliminar una compra
	 * @see com.factura.FacturaElectronica.Bo.CompraBo#eliminar(com.factura.FacturaElectronica.modelo.Compra)
	 */
	@Override
	public void eliminar(Compra compra) {
		compraDao.eliminar(compra);
	}

	/**
	 * Buscar por id
	 * @see com.factura.FacturaElectronica.Bo.CompraBo#findById(java.lang.Integer)
	 */
	@Override
	public Compra findById(Integer id) {
		return compraDao.findById(id);
	}

	/**
	 * Buscar por consecutivo y empresa
	 * @see com.factura.FacturaElectronica.Bo.CompraBo#findByConsecutivoAndEmpresa(java.lang.String, com.factura.FacturaElectronica.modelo.Empresa)
	 */
	@Override
	public Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		return compraDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
	}
	
	/**
	 * Elimina los detalles de una compra
	 * @see com.factura.FacturaElectronica.Bo.CompraBo#eliminarDetalleComprasPorSP(com.factura.FacturaElectronica.modelo.Compra)
	 */
	public void eliminarDetalleComprasPorSP(Compra compra) {
		compraDao.eliminarDetalleComprasPorSP(compra);
	}

}