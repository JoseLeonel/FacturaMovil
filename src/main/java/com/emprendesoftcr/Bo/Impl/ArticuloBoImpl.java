package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.CabysBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.TarifaIVAIBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.TarifaIVAI;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.CabysAct;
import com.emprendesoftcr.web.command.TotalInventarioCommand;
import com.google.gson.Gson;

/**
 * ArticuloBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */

@EnableTransactionManagement
@Service("articuloBo")
public class ArticuloBoImpl implements ArticuloBo {

	private Logger				log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	ArticuloDao						articuloDao;
	@Autowired
	public DataSource			dataSource;

	@Autowired
	private DataTableBo		dataTableBo;
	@Autowired
	private UsuarioBo			usuarioBo;
	
	@Autowired
	private TarifaIVAIBo	tarifaIVAIBo;

	@Autowired
	private CabysBo cabysBo;
	
	private JdbcTemplate	jdbcTemplate;

	@Transactional
	public void agregar(Articulo articulo) {

		articuloDao.agregar(articulo);
	}

	@Transactional
	public void modificar(Articulo articulo) {
		articuloDao.modificar(articulo);
	}

	@Transactional
	public void eliminar(Articulo articulo) {
		articuloDao.eliminar(articulo);
	}

	/**
	 * Busca la Articulo por descripcion y empresa
	 * @see com.factura.bo.ArticuloBo#buscarByDescripcionAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return articuloDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.ArticuloBo#buscar(java.lang.Integer)
	 */
	@Override
	public Articulo buscar(Long id) {
		return articuloDao.buscar(id);
	}

	/**
	 * Busqueda de Articulo por codigo y empresa
	 * @see com.factura.bo.ArticuloBo#buscarByCodigoAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa) {
		return articuloDao.buscarPorCodigoYEmpresa(codigo, empresa);
	}

	/**
	 * Obtener la Ganancia
	 * @throws Exception
	 * @see com.emprendesoftcr.Bo.ArticuloBo#porcentanjeDeGanancia(java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double porcentanjeDeGanancia(Double costo, Double iva, Double precio) throws Exception {
		try {
			return articuloDao.porcentanjeDeGanancia(costo, iva, precio);
		} catch (Exception e) {
			log.info("** Error  porcentanjeDeGanancia: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public Double sumarCantidad(Articulo articulo, Double cantidad) throws Exception {
		try {
			articulo.setCantidad(articulo.getCantidad() + cantidad);
			return articulo.getCantidad();

		} catch (Exception e) {
			log.info("** Error  sumarCantidad: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public Double restarCantidad(Articulo articulo, Double cantidad) throws Exception {
		try {
			articulo.setCantidad(articulo.getCantidad() - cantidad);
			return articulo.getCantidad();

		} catch (Exception e) {
			log.info("** Error  restarCantidad: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@Override
	public TotalInventarioCommand sumarInventarios(Integer idEmpresa, Date fechaInicial, Date FechaFinal) {
		return articuloDao.sumarInventarios(idEmpresa, fechaInicial, FechaFinal);
	}

	@Override
	public Collection<Articulo> articulosBy(Empresa empresa) {
		return articuloDao.articulosBy(empresa);
	}

	@Override
	public Collection<Articulo> articulosOrderCategoria(Empresa empresa) {
		return articuloDao.articulosOrderCategoria(empresa);
	}

	@Override
	public Collection<Articulo> findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(Empresa empresa, Categoria categoria, String estado, String minimoMaximo) {
		return articuloDao.findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(empresa, categoria, estado, minimoMaximo);
	}

	@Override
	public Collection<Articulo> articulosByCategoriaAndEmpresa(Integer idEmpresa, Long idCategoria) {

		return articuloDao.articulosByCategoriaAndEmpresa(idEmpresa, idCategoria);
	}

	@Override
	public List<Map<String, Object>> articulosByCabys(String descripcion, String codigo, Integer tipo, Integer idEmpresa, Integer cantidad) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT a.id,c.descripcion nomb_cate,a.codigo,a.cod_cabys,a.descripcion,a.impuesto,a.estado FROM articulos a\n" + "              inner join categorias c on c.id = a.categoria_id\n" + "              where a.empresa_id = :idEmpresa and a.descripcion like and a.codigo like and a.cod_cabys = '' " + " limit cantidad";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idEmpresa", idEmpresa);
		descripcion = descripcion == null ? Constantes.EMPTY : descripcion;
		tipo = tipo == null ? Constantes.ZEROS : tipo;
		codigo = codigo == null ? Constantes.EMPTY : codigo;
		if (descripcion.equals(Constantes.EMPTY)) {
			sql = sql.replaceAll(" and a.descripcion like", "");
		} else {
			sql = sql.replaceAll(" and a.descripcion like", " and a.descripcion like '%" + descripcion + "%'");
		}
		if (codigo.equals(Constantes.EMPTY)) {
			sql = sql.replaceAll(" and a.codigo like", "");
		} else {
			sql = sql.replaceAll(" and a.codigo like", " and a.codigo like '%" + codigo + "%'");
		}
		if (tipo.equals(1)) {
			sql = sql.replaceAll(" and a.cod_cabys = ''", " and a.cod_cabys =''");
		}
		if (tipo.equals(2)) {
			sql = sql.replaceAll(" and a.cod_cabys = ''", " and a.cod_cabys !=''");
		}
		if (tipo.equals(0)) {
			sql = sql.replaceAll(" and a.cod_cabys = ''", "");
		}
		cantidad = cantidad == null ? Constantes.ZEROS : cantidad;
		if (cantidad.equals(Constantes.ZEROS)) {
			sql = sql.replaceAll(" limit cantidad", "");
		} else {
			sql = sql.replaceAll(" limit cantidad", " limit " + cantidad);
		}

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);

		return listaObjetos;
	}

	@Override
	public RespuestaServiceDataTable<?> listarByCodigoArticulo(HttpServletRequest request, HttpServletResponse response, String codigoArt, String nombreUsuario) {
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {

			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter categoriaFilter = null;
		if (codigoArt != null) {
			if (!codigoArt.equals(Constantes.EMPTY)) {
				categoriaFilter = new JqGridFilter("codigo", "'" + codigoArt + "'", "=");
				delimitadores.addFiltro(categoriaFilter);
			}
		}

		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		@SuppressWarnings("rawtypes")
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			Articulo object = (Articulo) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				solicitudList.add(new ArticuloCommand(object));
			}
		}

		respuestaService.setRecordsTotal(total);
		respuestaService.setRecordsFiltered(total);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
	}

	@Transactional
	@Override
	public RespuestaServiceValidator<?> modificar(HttpServletRequest request, Articulo articulo, BindingResult result) {
		try {
			articulo.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articulo.setDescripcion(articulo.getDescripcion().replace("&", ""));
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setTipoImpuestoMag(articulo.getTipoImpuestoMag() == null ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			articulo.setImpuestoMag(articulo.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuestoMag());
			articulo.setCodigoTarifaMag(articulo.getCodigoTarifaMag() == null ? Constantes.EMPTY : articulo.getCodigoTarifaMag());
			articulo.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.BASE_IMPONIBLE_INACTIVO : articulo.getBaseImponible());
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (articulo.getTipoImpuesto() != null) {
				articulo.setTipoImpuesto(articulo.getTipoImpuesto().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuesto());
			}
			if (articulo.getTipoImpuestoMag() != null) {
				articulo.setTipoImpuestoMag(articulo.getTipoImpuestoMag().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			}
			if (articulo.getTipoCodigo() == null) {
				articulo.setTipoCodigo("04");
			}
			Articulo articuloBd = buscar(articulo.getId());
			Articulo articuloValidar = null;

			if (!articuloBd.getCodigo().equals(articulo.getCodigo().trim())) {
				articuloValidar = buscarPorCodigoYEmpresa(articulo.getCodigo().trim(), usuarioSesion.getEmpresa());
				if (articuloValidar != null) {
					result.rejectValue("codigo", "error.articulo.codigo.existe");
				}
				result.rejectValue("codigo", "error.articulo.codigo.no.modificarse");

			}
			if (articulo.getPrecioPublico() == null) {
				result.rejectValue("precioPublico", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getPrecioPublico() == 0) {
				result.rejectValue("precioPublico", "error.articulo.precioPublico.mayorCero");
			}

			if (!articulo.getCodigoTarifa().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifa());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifa", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuesto())) {
						result.rejectValue("impuesto", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
					} else {
						articulo.setImpuesto(tarifaIVAI.getMonto());
					}
				}
			}
			if (!articulo.getCodigoTarifaMag().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifaMag());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifaMag", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuestoMag())) {
						result.rejectValue("impuestoMag", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
					} else {
						articulo.setImpuestoMag(tarifaIVAI.getMonto());
					}
				}
			}
			if (!articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && !articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_ARTICULO)) {
					if (articulo.getImpuesto().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto", "error.articulo.codigo.impuesto.no.tiene.porcentaje.correcto");

					}
				}

			}
			if (!articulo.getTipoImpuestoMag().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuestoMag().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && !articulo.getTipoImpuestoMag().equals(Constantes.TIPO_IMPUESTO_VENTA_ARTICULO)) {
					if (articulo.getImpuestoMag().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto1", "error.articulo.codigo.impuesto.no.tiene.porcentaje.correcto");

					}
				}

			}

			if (articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				articulo.setImpuesto(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa(Constantes.EMPTY);
			}
			if (articulo.getTipoImpuestoMag().equals(Constantes.EMPTY)) {
				articulo.setImpuestoMag(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifaMag(Constantes.EMPTY);
			}
			if (!articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				if (articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO)) {
					if (!articulo.getImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO_VALOR)) {
						result.rejectValue("tipoImpuesto", "error.articulo.tipoImpuesto1.selectivoConsumo");
					}

				}
				if (!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
					if (articulo.getBaseImponible().equals(Constantes.BASE_IMPONIBLE_ACTIVO)) {
						result.rejectValue("tipoImpuesto", "error.articulo.tipoImpuesto1.base.imponible.incorrecta");
					}
				}
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Gson gson = new Gson();
			if (articulo.getDatosCabys() != null && !articulo.getDatosCabys().equals(Constantes.EMPTY)) {
				JSONObject json = (JSONObject) new JSONParser().parse(articulo.getDatosCabys());
				CabysAct cabysAct = gson.fromJson(json.toString(), CabysAct.class);
				Cabys cabysBD = cabysBo.findByCodigo(cabysAct.getCodigo(), usuarioSesion.getEmpresa());
				if (cabysBD == null && cabysAct.getCodigo() != null && !cabysAct.getCodigo().equals(Constantes.EMPTY)) {
					Cabys cabys = new Cabys();
					cabys.setId(null);
					cabys.setCodigo(cabysAct.getCodigo());
					cabys.setCreated_at(new Date());
					cabys.setUpdated_at(new Date());
					cabys.setDescripcion(cabysAct.getDescripcion());
					cabys.setEmpresa(usuarioSesion.getEmpresa());
					cabys.setOrigen(FacturaElectronicaUtils.convertirStringToblod(cabysAct.getOrigenSTR()));
					cabys.setUri(cabysAct.getUri());
					cabysBo.agregar(cabys);

				}
			}
			articuloBd.setMaximo(articulo.getMaximo() == null ? Constantes.ZEROS_DOUBLE : articulo.getMaximo());
			articuloBd.setMinimo(articulo.getMinimo() == null ? Constantes.ZEROS_DOUBLE : articulo.getMinimo());
			articuloBd.setUpdated_at(new Date());
			articuloBd.setCosto(articulo.getCosto() == null ? Constantes.ZEROS_DOUBLE : articulo.getCosto());
			articuloBd.setMaximo(articulo.getMaximo());
			articuloBd.setMinimo(articulo.getMinimo());
			articuloBd.setMarca(articulo.getMarca());
			articuloBd.setDescripcion(articulo.getDescripcion());
			articuloBd.setContable(articulo.getContable());
			articuloBd.setCategoria(articulo.getCategoria());
			articuloBd.setUnidadMedida(articulo.getUnidadMedida());
			articuloBd.setTipoCodigo(articulo.getTipoCodigo());
			articuloBd.setEstado(articulo.getEstado());
			articuloBd.setGananciaPrecioPublico(articulo.getGananciaPrecioPublico() != null ? articulo.getGananciaPrecioPublico() : Constantes.ZEROS_DOUBLE);
			articuloBd.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() != null ? articulo.getGananciaPrecioMayorista() : Constantes.ZEROS_DOUBLE);
			articuloBd.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() != null ? articulo.getGananciaPrecioEspecial() : Constantes.ZEROS_DOUBLE);
			articuloBd.setPrecioPublico(articulo.getPrecioPublico());
			articuloBd.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articuloBd.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articuloBd.setUsuario(usuarioSesion);
			articuloBd.setCodigo(articulo.getCodigo().trim());
			articuloBd.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articuloBd.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articuloBd.setComanda(articulo.getComanda());
			articuloBd.setPrioridad(articulo.getPrioridad());
			articuloBd.setTipoImpuestoMag(articulo.getTipoImpuestoMag() == null ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			articuloBd.setImpuestoMag(articulo.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuestoMag());
			articuloBd.setPesoTransporte(articulo.getPesoTransporte() == null ? Constantes.ZEROS_DOUBLE : articulo.getPesoTransporte());
			articuloBd.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articuloBd.setCodigoTarifaMag(articulo.getCodigoTarifaMag() == null ? Constantes.EMPTY : articulo.getCodigoTarifaMag());
			articuloBd.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());
			articuloBd.setCodigoCabys(articulo.getCodigoCabys() != null ? articulo.getCodigoCabys() : Constantes.EMPTY);
			modificar(articuloBd);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

}