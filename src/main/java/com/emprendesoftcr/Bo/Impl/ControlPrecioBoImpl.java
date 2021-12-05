package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ControlPrecioBo;
import com.emprendesoftcr.Bo.ControlPrecioObseBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Dao.ControlPrecioDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.ControlPrecioArticulo;
import com.emprendesoftcr.modelo.ControlPrecioObse;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;


@EnableTransactionManagement
@Service("controlPrecioBo")
public class ControlPrecioBoImpl implements ControlPrecioBo {

	@Autowired
	private ControlPrecioDao		controlPrecioDao;
	@Autowired
	private UsuarioBo						usuarioBo;

	@Autowired
	private ControlPrecioObseBo	controlPrecioObseBo;

	@Transactional
	@Override
	public void agregar(ControlPrecioArticulo controlPrecioArticulo) {
		controlPrecioDao.agregar(controlPrecioArticulo);

	}

	@Transactional
	@Override
	public void modificar(ControlPrecioArticulo controlPrecioArticulo) {
		controlPrecioDao.modificar(controlPrecioArticulo);

	}

	@Transactional
	@Override
	public void eliminar(ControlPrecioArticulo controlPrecioArticulo) {
		controlPrecioDao.eliminar(controlPrecioArticulo);

	}

	@Transactional
	@Override
	public ControlPrecioArticulo buscar(Long id) {

		return controlPrecioDao.buscar(id);
	}

	@Override
	public void agregarControlPrecio(Articulo articuloCambio, Articulo articuloActual, String nota, String rutaArchivoCompra, String consecutivoCompra, Usuario usuarioResponsable) {
		ControlPrecioArticulo controlPrecioArticulo = new ControlPrecioArticulo();
		if (usuarioBo.isAdministrador_cajero(usuarioResponsable) || usuarioBo.isAdministrador_empresa(usuarioResponsable) || usuarioBo.isAdministrador_restaurante(usuarioResponsable)) {
			controlPrecioArticulo.setEstado(Constantes.ESTADO_ACEPTADO_ADMINISTRADOR_CAMBIO_PRECIO);
		} else {
			controlPrecioArticulo.setEstado(Constantes.ESTADO_PENDIENTE_ADMINISTRADOR_CAMBIO_PRECIO);

		}

		if (articuloCambio.getPrecioPublico() != articuloActual.getPrecioPublico() ) {

			controlPrecioArticulo.setArticulo(articuloActual);
			controlPrecioArticulo.setCostoNuevo(articuloActual.getCosto());
			controlPrecioArticulo.setPrecioPublicoNuevo(articuloCambio.getPrecioPublico());
			
			controlPrecioArticulo.setResponsableCambioPrecio(usuarioResponsable);
			controlPrecioArticulo.setResponbleAceptarPrecio(usuarioResponsable);
			controlPrecioArticulo.setCreated_at(new Date());
			controlPrecioArticulo.setUpdated_at(new Date());
			controlPrecioArticulo.setGananciaNueva(articuloCambio.getGananciaPrecioPublico());
			controlPrecioArticulo.setRutaArchivoCompra(rutaArchivoCompra);
			controlPrecioArticulo.setConsecutivo(consecutivoCompra);
			agregar(controlPrecioArticulo);
			// Agregar observacion
			ControlPrecioObse controlPrecioObse = new ControlPrecioObse();
			controlPrecioObse.setControlPrecioArticulo(controlPrecioArticulo);
			controlPrecioObse.setDescripcion(nota);
			controlPrecioObse.setCreated_at(new Date());
			controlPrecioObse.setUpdated_at(new Date());
			controlPrecioObse.setUsuario(usuarioResponsable);
			controlPrecioObseBo.agregar(controlPrecioObse);
		}

	}
}
