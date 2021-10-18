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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.CabysBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.KardexBo;
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
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.CabysAct;
import com.emprendesoftcr.web.command.CambiarPrecioArticuloCommand;
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
	@Autowired
	private KardexBo kardexBo;
	
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
	public Articulo buscarPorCodigoSecundarioYEmpresa(String codigo, Empresa empresa) {
		
		return articuloDao.buscarPorCodigoSecundarioYEmpresa(codigo, empresa);
	}

	
	
	
	@SuppressWarnings("unchecked")
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
		categoriaFilter = new JqGridFilter("cantidadPaquete", "'" + Constantes.ARTICULO_PAQUETE_TIPO_INACTIVO + "'", "=");
		delimitadores.addFiltro(categoriaFilter);
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
			if(usuarioSesion.getEmpresa().getNoFacturaElectronica() != null && usuarioSesion.getEmpresa().getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
				if (articulo.getCodigoCabys() != null && articulo.getCodigoCabys().equals(Constantes.EMPTY)) {
					result.rejectValue("codigo", "error.articulo.cabys.es.requerido");					
				}
			}
			if (articulo.getCantidadPaquete() != null && articulo.getCantidadPaquete().equals(Constantes.ARTICULO_PAQUETE_TIPO_ACTIVO)) {
				if (articulo.getCantidad().equals(Constantes.ZEROS_DOUBLE)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.indique.cantidad.paquete", result.getAllErrors());
				}
				if (articulo.getCodigoSecundario() == null || articulo.getCodigoSecundario().equals(Constantes.EMPTY)) {
					result.rejectValue("codigoSecundario", "error.articulo.codigo.secundario");
				} else {
						articuloValidar = buscarPorCodigoSecundarioYEmpresa(articulo.getCodigoSecundario().trim(), usuarioSesion.getEmpresa());
						if (articuloValidar != null) {
							if(!articuloBd.getCodigo().equals(articuloValidar.getCodigo())) {
								result.rejectValue("codigoSecundario", "error.articulo.codigo.secundario.ya.existe");	
							}
							
						}
						articuloValidar = buscarPorCodigoYEmpresa(articulo.getCodigoSecundario().trim(), usuarioSesion.getEmpresa());
						if (articuloValidar == null) {
							result.rejectValue("codigoSecundario", "error.articulo.codigo.no.existe.inve");
						}
//						articuloValidar = articuloBo.buscarPorCodigoYEmpresa(articulo.getCodigoSecundario().trim(), usuarioSesion.getEmpresa());
//						if (articuloValidar != null) {
//							result.rejectValue("codigoSecundario", "error.articulo.codigo.existe");
//						}
						
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
			articuloBd.setTipoFacturar(articulo.getTipoFacturar());
			articuloBd.setCodigoSecundario(articulo.getCodigoSecundario());
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
			if (articulo.getCantidadPaquete() != null && articulo.getCantidadPaquete().equals(Constantes.ARTICULO_PAQUETE_TIPO_ACTIVO)) {
				if (articulo.getCantidad() > Constantes.ZEROS_DOUBLE) {
					articuloBd.setCantidad(articulo.getCantidad());
				}
				articuloBd.setCantidadPaquete(Constantes.ARTICULO_PAQUETE_TIPO_ACTIVO);
				articuloBd.setCodigoSecundario(articulo.getCodigoSecundario());
			}else {
				articuloBd.setCantidadPaquete(Constantes.ARTICULO_PAQUETE_TIPO_INACTIVO);
				articuloBd.setCodigoSecundario(Constantes.EMPTY);
			}
			articuloBd.setPrecioSugerido(articulo.getPrecioSugerido() != null?articulo.getPrecioSugerido():Constantes.ZEROS_DOUBLE);
			modificar(articuloBd);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> agregar(HttpServletRequest request, Articulo articulo, BindingResult result) {
		
		try {
			articulo.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articulo.setTipoImpuestoMag(articulo.getTipoImpuestoMag() == null ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setImpuestoMag(articulo.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuestoMag());
			articulo.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setCodigoTarifaMag(articulo.getCodigoTarifaMag() == null ? Constantes.EMPTY : articulo.getCodigoTarifaMag());
			articulo.setTipoImpuestoMag(articulo.getTipoImpuestoMag() == null ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			articulo.setImpuestoMag(articulo.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuestoMag());
			articulo.setCodigoTarifaMag(articulo.getCodigoTarifaMag() == null ? Constantes.EMPTY : articulo.getCodigoTarifaMag());
			articulo.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());
			articulo.setEstado(articulo.getEstado() == null ? Constantes.EMPTY : articulo.getEstado());
			articulo.setTipoFacturar(articulo.getTipoFacturar() == null?Constantes.ZEROS:articulo.getTipoFacturar());
			if (articulo.getEstado().equals(Constantes.ESTADO_INACTIVO)) {
				result.rejectValue("estado", "error.articulo.inactivo.agregar");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.inactivo.agregar", result.getAllErrors());

			}

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBd = null;

			articuloBd = buscarPorCodigoYEmpresa(articulo.getCodigo().trim(), usuarioSesion.getEmpresa());
			if (articuloBd != null) {
				result.rejectValue("codigo", "error.articulo.codigo.existe");
			}
			if (articulo.getPrecioPublico() == null) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getPrecioPublico() == 0) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getCantidad() != null) {
				if (articulo.getCantidad() == Constantes.ZEROS_DOUBLE) {
					articulo.setCantidad(Constantes.ZEROS_DOUBLE);
				}
			}
			if (articulo.getCantidad() == null) {
				articulo.setCantidad(Constantes.ZEROS_DOUBLE);
			}

			if (articulo.getTipoImpuesto() != null) {
				articulo.setTipoImpuesto(articulo.getTipoImpuesto().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuesto());
			}
			if (articulo.getTipoImpuestoMag() != null) {
				articulo.setTipoImpuestoMag(articulo.getTipoImpuestoMag().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			}
			if (!articulo.getCodigoTarifa().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifa());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifa", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuesto())) {
						result.rejectValue("codigoTarifa", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
					} else {
						articulo.setImpuesto(tarifaIVAI.getMonto());
					}
				}
			}
			if (!articulo.getCodigoTarifaMag().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifaMag());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifa1", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuestoMag())) {
						result.rejectValue("impuesto1", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
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

			if (articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				articulo.setImpuesto(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa(Constantes.EMPTY);
			}
			if (articulo.getTipoImpuestoMag().equals(Constantes.EMPTY)) {
				articulo.setImpuestoMag(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifaMag(Constantes.EMPTY);
			}
			if (!articulo.getTipoImpuestoMag().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuestoMag().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
					if (articulo.getImpuestoMag().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto1", "error.articulo.tipoImpuesto1.cero");
					}
				}
				if (articulo.getTipoImpuestoMag().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO)) {
					if (!articulo.getImpuestoMag().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO_VALOR)) {
						result.rejectValue("tipoImpuesto1", "error.articulo.tipoImpuesto1.selectivoConsumo");
					}

				}
			}
			if (!articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				if (articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO)) {
					if (!articulo.getImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO_VALOR)) {
						result.rejectValue("tipoImpuesto1", "error.articulo.tipoImpuesto1.selectivoConsumo");
					}

				}
				if (!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
					if (articulo.getBaseImponible().equals(Constantes.BASE_IMPONIBLE_ACTIVO)) {
						result.rejectValue("tipoImpuesto", "error.articulo.tipoImpuesto1.base.imponible.incorrecta");
					}
				}

			}
			
			
				if(usuarioSesion.getEmpresa().getNoFacturaElectronica() != null && usuarioSesion.getEmpresa().getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
					if (articulo.getCodigoCabys() != null && articulo.getCodigoCabys().equals(Constantes.EMPTY)) {
						result.rejectValue("codigo", "error.articulo.cabys.es.requerido");					
					}
				}
				if (articulo.getCantidad() != null) {
					if (articulo.getCantidad() == Constantes.ZEROS_DOUBLE) {
						articulo.setCantidad(Constantes.ZEROS_DOUBLE);
					}
				}
				if (articulo.getCantidad() == null) {
					articulo.setCantidad(Constantes.ZEROS_DOUBLE);
				}
				if (articulo.getCantidadPaquete() != null && articulo.getCantidadPaquete().equals(Constantes.ARTICULO_PAQUETE_TIPO_ACTIVO)) {
					if (articulo.getCantidad().equals(Constantes.ZEROS_DOUBLE)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.indique.cantidad.paquete", result.getAllErrors());
					}
					if (articulo.getCodigoSecundario() == null || articulo.getCodigoSecundario().equals(Constantes.EMPTY)) {
						result.rejectValue("codigoSecundario", "error.articulo.codigo.secundario");
					} else {
						articuloBd = buscarPorCodigoSecundarioYEmpresa(articulo.getCodigoSecundario().trim(), usuarioSesion.getEmpresa());
						if (articuloBd != null) {
							result.rejectValue("codigoSecundario", "error.articulo.codigo.secundario.ya.existe");
						}
						articuloBd = buscarPorCodigoYEmpresa(articulo.getCodigoSecundario().trim(), usuarioSesion.getEmpresa());
						if (articuloBd == null) {
							result.rejectValue("codigoSecundario", "error.articulo.codigo.no.existe.inve");
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
				if (cabysBD == null) {
					Cabys cabys = new Cabys();
					cabys.setId(null);
					cabys.setCodigo(cabysAct.getCodigo());
					cabys.setCreated_at(new Date());
					cabys.setUpdated_at(new Date());
					cabys.setDescripcion(cabysAct.getDescripcion());
					cabys.setEmpresa(usuarioSesion.getEmpresa());
					cabys.setOrigen(FacturaElectronicaUtils.convertirStringToblod(cabysAct.getOrigenSTR()));
					cabys.setUri(cabysAct.getUri());
					if(cabys.getOrigen() != null) {
						cabysBo.agregar(cabys);	
					}
					

				}
			}
			articulo.setDescripcion(articulo.getDescripcion().replace("&", ""));
			articulo.setCreated_at(new Date());
			articulo.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articulo.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articulo.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articulo.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getGananciaPrecioEspecial());
			articulo.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getGananciaPrecioMayorista());
			articulo.setCantidad(articulo.getCantidad() == null ? Constantes.ZEROS_DOUBLE : articulo.getCantidad());
			articulo.setCosto(articulo.getCosto() == null ? Constantes.ZEROS_DOUBLE : articulo.getCosto());

			articulo.setEmpresa(usuarioSesion.getEmpresa());
			articulo.setUpdated_at(new Date());
			articulo.setEstado(Constantes.ESTADO_ACTIVO);
			articulo.setGananciaPrecioPublico(articulo.getGananciaPrecioPublico() != null ? articulo.getGananciaPrecioPublico() : Constantes.ZEROS_DOUBLE);
			articulo.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() != null ? articulo.getGananciaPrecioMayorista() : Constantes.ZEROS_DOUBLE);
			articulo.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() != null ? articulo.getGananciaPrecioEspecial() : Constantes.ZEROS_DOUBLE);
			articulo.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articulo.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setUsuario(usuarioSesion);
			articulo.setTipoImpuestoMag(articulo.getTipoImpuestoMag() == null ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			articulo.setImpuestoMag(articulo.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuestoMag());
			articulo.setPesoTransporte(articulo.getPesoTransporte() == null ? Constantes.ZEROS_DOUBLE : articulo.getPesoTransporte());
			articulo.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setCodigoTarifaMag(articulo.getCodigoTarifaMag() == null ? Constantes.EMPTY : articulo.getCodigoTarifaMag());
			articulo.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());
			articulo.setMaximo(articulo.getMaximo() == null ? Constantes.ZEROS : articulo.getMaximo());
			articulo.setMinimo(articulo.getMinimo() == null ? Constantes.ZEROS : articulo.getMinimo());
			articulo.setCodigoCabys(articulo.getCodigoCabys() != null ? articulo.getCodigoCabys() : Constantes.EMPTY);
			agregar(articulo);

			if (usuarioSesion.getEmpresa().getTieneInventario().equals(Constantes.ESTADO_ACTIVO)) {
				if (!articulo.getCantidad().equals(Constantes.ZEROS_DOUBLE)) {
					kardexBo.entrada(articulo, Constantes.ZEROS_DOUBLE, articulo.getCantidad(), Constantes.OBSERVACION_INICIAL_INVENTARIO_NUEVO, Constantes.CONSECUTIVO_INICIAL_INVENTARIO_NUEVO, Constantes.KARDEX_TIPO_ENTRADA, Constantes.MOTIVO_INICIAL_INVENTARIO_NUEVO, usuarioSesion);

				}

			}

			Articulo articuloNuevo = buscar(articulo.getId());
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.agregar.correctamente", articuloNuevo);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> cambiarPrecio(HttpServletRequest request, HttpServletResponse response,
			 Articulo articulo, Double precioPublico, String codigo, String tipoImpuesto,
			Double impuesto, String descripcion, String tipoCodigo, String unidadMedida, BindingResult result) {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setImpuestoMag(
					articulo.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuestoMag());
			articulo.setCodigoTarifa(
					articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setCodigoTarifaMag(
					articulo.getCodigoTarifaMag() == null ? Constantes.EMPTY : articulo.getCodigoTarifaMag());
			Articulo articuloBD = buscarPorCodigoYEmpresa(codigo, usuario.getEmpresa());

			if (articuloBD == null) {
				result.rejectValue("codigo", "error.articulo.codigo.no.existe");
			}

			if(usuario.getEmpresa().getNoFacturaElectronica() != null && usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
				if (articulo.getCodigoCabys() != null && articulo.getCodigoCabys().equals(Constantes.EMPTY)) {
					result.rejectValue("codigo", "error.articulo.cabys.es.requerido");					
				}
			}
			
		

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion",
						result.getAllErrors());
			}

			if (tipoCodigo == null) {
				articuloBD.setTipoCodigo("04");
			} else {
				articuloBD.setTipoCodigo(tipoCodigo);
			}
			if (articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				articulo.setImpuesto(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa(Constantes.EMPTY);
			}
			if (articulo.getTipoImpuestoMag().equals(Constantes.EMPTY)) {
				articulo.setImpuestoMag(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifaMag(Constantes.EMPTY);
			}
			articuloBD.setUpdated_at(new Date());
			articuloBD.setCosto(articulo.getCosto() == null ? Constantes.ZEROS_DOUBLE : articulo.getCosto());
			articuloBD.setMarca(articulo.getMarca());
			articuloBD.setDescripcion(articulo.getDescripcion());
			articuloBD.setContable(articulo.getContable());
			articuloBD.setCategoria(articulo.getCategoria());
			articuloBD.setUnidadMedida(articulo.getUnidadMedida());
			articuloBD.setTipoCodigo(articulo.getTipoCodigo());
			articuloBD.setEstado(articulo.getEstado());
			articuloBD.setGananciaPrecioPublico(
					articulo.getGananciaPrecioPublico() != null ? articulo.getGananciaPrecioPublico()
							: Constantes.ZEROS_DOUBLE);
			articuloBD.setPrecioPublico(articulo.getPrecioPublico());
			articuloBD.setUsuario(usuario);
			articuloBD.setCodigo(articulo.getCodigo().trim());
			articuloBD.setTipoImpuesto(
					articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articuloBD.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articuloBD.setComanda(articulo.getComanda());
			articuloBD.setPrioridad(articulo.getPrioridad());
			articuloBD.setTipoImpuestoMag(
					articulo.getTipoImpuestoMag() == null ? Constantes.EMPTY : articulo.getTipoImpuestoMag());
			articuloBD.setImpuestoMag(
					articulo.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuestoMag());
			articuloBD.setPesoTransporte(
					articulo.getPesoTransporte() == null ? Constantes.ZEROS_DOUBLE : articulo.getPesoTransporte());
			articuloBD.setCodigoTarifa(
					articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articuloBD.setCodigoTarifaMag(
					articulo.getCodigoTarifaMag() == null ? Constantes.EMPTY : articulo.getCodigoTarifaMag());
			articuloBD.setBaseImponible(
					articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());
			modificar(articuloBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> cambiarPrecioArticulo(HttpServletRequest request, HttpServletResponse response,
			ModelMap model, CambiarPrecioArticuloCommand cambiarPrecioArticuloCommand, BindingResult result) {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBD = buscar(cambiarPrecioArticuloCommand.getId());
			if (articuloBD == null) {
				result.rejectValue("codigo", "error.articulo.codigo.no.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion",
						result.getAllErrors());
			}
			String descripcion = cambiarPrecioArticuloCommand.getDescripcion() == null ? Constantes.EMPTY
					: cambiarPrecioArticuloCommand.getDescripcion();
			articuloBD.setUpdated_at(new Date());
			articuloBD.setDescripcion(descripcion.length() > 0 ? descripcion : articuloBD.getDescripcion());
			articuloBD.setGananciaPrecioPublico(Utils.getPorcentajeGananciaArticulo(articuloBD.getCosto(),
					cambiarPrecioArticuloCommand.getPrecioPublico(), articuloBD.getImpuesto()));
			articuloBD.setPrecioPublico(cambiarPrecioArticuloCommand.getPrecioPublico());
			articuloBD.setUsuario(usuario);
			modificar(articuloBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> findArticuloByCodigojax(HttpServletRequest request, Articulo articulo,
			HttpServletResponse response, String codigoArticulo, BindingResult result) {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBD = buscarPorCodigoYEmpresa(codigoArticulo, usuarioSesion.getEmpresa());
			ArticuloCommand articuloCommand = articuloBD == null ? null : new ArticuloCommand(articuloBD);

			if (articuloCommand == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.codigo.no.existe",
						result.getAllErrors());
			}
			if (articuloCommand.getCodigoCabys() == null && usuarioSesion.getEmpresa().getNoFacturaElectronica()
					.equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("articulo.no.existe.codigo.cabys",
						result.getAllErrors());
			}
			if (articuloCommand.getCodigoCabys().equals(Constantes.EMPTY) && usuarioSesion.getEmpresa()
					.getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("articulo.no.existe.codigo.cabys",
						result.getAllErrors());
			}
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

}