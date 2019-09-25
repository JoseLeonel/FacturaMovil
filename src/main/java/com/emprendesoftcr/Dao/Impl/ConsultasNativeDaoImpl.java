package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ConsultasNativeDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.sqlNativo.BaseNativeQuery;
import com.emprendesoftcr.modelo.sqlNativo.CompraSimplificadaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaComprasIvaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaGananciaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaIVANative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaComprobarNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasImpuestoServicioNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasNativa;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstadoAndUsuario;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturada;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturadaAndUsuario;

@Repository("consultasNativeDao")
public class ConsultasNativeDaoImpl implements ConsultasNativeDao {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<HaciendaNative> findByEmpresaAndEstado(Empresa empresa, String fechaInicial, String fechaFinal) {
		String queryStr = getQueryBase(HaciendaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":fechaInicial", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":fechaFinal", "'" + fechaFinal + "'");
		Query query = entityManager.createNativeQuery(queryStr, HaciendaNative.class);
		return (Collection<HaciendaNative>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<HaciendaNativeByEmpresaAndFechaAndCliente> findByEmpresaAndFechaAndCliente(Empresa empresa, String fechaInicial, String fechaFinal, String cedula) {
		String queryStr = getQueryBase(HaciendaNativeByEmpresaAndFechaAndCliente.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":fechaInicial", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":fechaFinal", "'" + fechaFinal + "'");
		queryStr = queryStr.replaceAll(":CEDULA", cedula.toString());
		Query query = entityManager.createNativeQuery(queryStr, HaciendaNativeByEmpresaAndFechaAndCliente.class);
		return (Collection<HaciendaNativeByEmpresaAndFechaAndCliente>) query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndEstadoAndUsuario> findByProformasByEmpresaAndEstadoAndUsuario(Empresa empresa, Integer estado, Integer idUsuario) {
		String queryStr = getQueryBase(ProformasByEmpresaAndEstadoAndUsuario.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":ESTADO", "'" + estado + "'");
		queryStr = queryStr.replaceAll(":IDUSUARIO", "'" + idUsuario + "'");
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndEstadoAndUsuario.class);
		return (Collection<ProformasByEmpresaAndEstadoAndUsuario>) query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndFacturadaAndUsuario> findByProformasByEmpresaFacturadaAndUsuario(Empresa empresa, Integer idUsuario) {
		String queryStr = getQueryBase(ProformasByEmpresaAndFacturadaAndUsuario.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":IDUSUARIO", "'" + idUsuario + "'");
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndFacturadaAndUsuario.class);
		return (Collection<ProformasByEmpresaAndFacturadaAndUsuario>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndEstado> findByProformasByEmpresaAndEstado(Empresa empresa, Integer estado) {
		String queryStr = getQueryBase(ProformasByEmpresaAndEstado.class);
		queryStr = empresa != null ? queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString()) : queryStr.replaceAll("facturas.empresa_id = :ID_EMPRESA and", "");
		queryStr = queryStr.replaceAll(":ESTADO", "'" + estado + "'");

		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndEstado.class);
		return (Collection<ProformasByEmpresaAndEstado>) query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndFacturada> findByProformasByEmpresaFacturada(Empresa empresa) {
		String queryStr = getQueryBase(ProformasByEmpresaAndFacturada.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());

		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndFacturada.class);
		return (Collection<ProformasByEmpresaAndFacturada>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<FacturasDelDiaNative> findByFacturasDelDia(Empresa empresa, Integer idusuario, String estado, String fecha) {
		String queryStr = getQueryBase(FacturasDelDiaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		if (idusuario > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("facturas.usuario_id =", " facturas.usuario_id ='" + idusuario.toString() + "' ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.usuario_id =", " ");
		}

		queryStr = queryStr.replaceAll(":ESTADO", estado.toString());
		queryStr = queryStr.replaceAll(":FECHA", "'" + fecha + "'");

		Query query = entityManager.createNativeQuery(queryStr, FacturasDelDiaNative.class);
		return (Collection<FacturasDelDiaNative>) query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public Collection<FacturasSinNotaCreditoNative> findByFacturasAnulacion(Empresa empresa, Integer idusuario, String estado, String fechaInicial, String fechaFinal, Long idCliente,String codigo) {
		String queryStr = getQueryBase(FacturasSinNotaCreditoNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":ESTADO", estado.toString());
		queryStr = queryStr.replaceAll(":FECHAINICIAL", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":FECHAFINAL", "'" + fechaFinal + "'");
		if (idusuario > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("facturas.usuario_id =", " facturas.usuario_id ='" + idusuario.toString() + "' ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.usuario_id =", " ");
		}
		if (idCliente > Constantes.ZEROS_LONG) {
			queryStr = queryStr.replaceAll("and facturas.cliente_id =", " and facturas.cliente_id ='" + idCliente.toString() + "' ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.cliente_id = ", " ");
		}
		if(codigo != null) {
			if(codigo.equals(Constantes.EMPTY)) {
				queryStr = queryStr.replaceAll("and detalles.codigo ", " ");
				queryStr = queryStr.replaceAll("inner join detalles on detalles.factura_id = facturas.id ", " ");
				
			}else {
				queryStr = queryStr.replaceAll("and detalles.codigo","and detalles.codigo ='" + codigo.toString() + "' ");
			}
		}

		Query query = entityManager.createNativeQuery(queryStr, FacturasSinNotaCreditoNative.class);
		return (Collection<FacturasSinNotaCreditoNative>) query.getResultList();

	}

	/**
	 * Consulta ventas
	 */
	public Collection<ConsultaIVANative> findByEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial, String fechaFinal, Integer estado, Integer codigoActividadComercial) {

		String queryStr = getQueryBase(ConsultaIVANative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":FECHAINICIAL", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":FECHAFINAL", "'" + fechaFinal + "'");
		queryStr = queryStr.replaceAll(":ESTADO", estado.toString());
		queryStr = queryStr.replaceAll(":ACT_COMERCIAL", " in ('" + codigoActividadComercial.toString() + "')");
		Query query = entityManager.createNativeQuery(queryStr, ConsultaIVANative.class);
		return (Collection<ConsultaIVANative>) query.getResultList();
	}

	/**
	 * Consulta compras
	 */
	public Collection<ConsultaComprasIvaNative> findByComprasEmpresaAndEstadoAndFechasAndActividadComercial(Empresa empresa, String fechaInicial, String fechaFinal, Integer estado, Integer codigoActividadComercial) {

		String queryStr = getQueryBase(ConsultaComprasIvaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":FECHAINICIAL", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":FECHAFINAL", "'" + fechaFinal + "'");
		queryStr = queryStr.replaceAll(":ESTADO", estado.toString());
		queryStr = queryStr.replaceAll(":ACT_COMERCIAL", "in ('" + codigoActividadComercial.toString() + "')");
		Query query = entityManager.createNativeQuery(queryStr, ConsultaComprasIvaNative.class);
		return (Collection<ConsultaComprasIvaNative>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<CompraSimplificadaNative> findComprasSimplificadasByFechaAndEstadoAndEmpresa(Empresa empresa, String fechaInicial, String fechaFinal, Long idProveedor, Integer estado, Integer idUsuario) {
		String queryStr = getQueryBase(CompraSimplificadaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":Estado", estado.toString());
		queryStr = queryStr.replaceAll(":FECHAINICIAL", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":FechaFinal", "'" + fechaFinal + "'");
		if (idUsuario > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("compra_simpli.usuario_id =", " compra_simpli.usuario_id ='" + idUsuario.toString() + "' ");
		} else {
			queryStr = queryStr.replaceAll("and compra_simpli.usuario_id =", " ");
		}
		if (idProveedor > Constantes.ZEROS_LONG) {
			queryStr = queryStr.replaceAll("and compra_simpli.proveedorsimpl_id =", " and compra_simpli.proveedorsimpl_id ='" + idProveedor.toString() + "' ");
		} else {
			queryStr = queryStr.replaceAll("and compra_simpli.proveedorsimpl_id = ", " ");
		}

		Query query = entityManager.createNativeQuery(queryStr, CompraSimplificadaNative.class);
		return (Collection<CompraSimplificadaNative>) query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<HaciendaComprobarNative> findByComprabarDocumentoPendienteaceptar() {
		String queryStr = getQueryBase(HaciendaComprobarNative.class);
		Query query = entityManager.createNativeQuery(queryStr, HaciendaComprobarNative.class);
		return (Collection<HaciendaComprobarNative>) query.getResultList();

	}

	private static <T> String getQueryBase(Class<T> claseObjecto) {
		return ((claseObjecto).getDeclaredAnnotationsByType(BaseNativeQuery.class))[0].query();
	}

	@Override
	public Collection<ListarFacturasNativa> findByFacturasAndFechaAndTipoDocAndUsuario(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, Cliente cliente, String tipoDocumento, String actividadComercial) {
		String queryStr = getQueryBase(ListarFacturasNativa.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());

		queryStr = queryStr.replaceAll(":fechaInicial", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":fechaFinal", "'" + fechaFinal + "'");
		
		if (actividadComercial.equals(Constantes.COMBO_TODOS)) {
			queryStr = queryStr.replaceAll("and facturas.act_comercial", "");
				
		}else {
			queryStr = queryStr.replaceAll("and facturas.act_comercial", " and facturas.act_comercial in ('" + actividadComercial + "') ");
		}
		
		if (idUsuario > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("and facturas.usuario_id", "and facturas.usuario_id ='" + idUsuario.toString() + "' ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.usuario_id", " ");
		}
		if (cliente != null) {
			queryStr = queryStr.replaceAll("and facturas.cliente_id", " and facturas.cliente_id =" + cliente.getId().toString() + " ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.cliente_id ", " ");
		}
		if (!tipoDocumento.equals(Constantes.COMBO_TODOS)) {
			queryStr = queryStr.replaceAll("and facturas.tipo_doc", " and facturas.tipo_doc in ('" + tipoDocumento + "') ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.tipo_doc ", "and facturas.tipo_doc in ('04','86','87','01','03') ");
		}

		if (estado > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("and facturas.estado", " and facturas.estado in (" + estado + ") ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.estado", " and facturas.estado in (" + "2,6,7,5" + ") ");
		}

		Query query = entityManager.createNativeQuery(queryStr, ListarFacturasNativa.class);
		return (Collection<ListarFacturasNativa>) query.getResultList();

	}

	@Override
	public Collection<ConsultaGananciaNative> findByDetallesGanancia(Empresa empresa, Cliente cliente, Integer estado, String fechaInicial, String fechaFinal, String actividadComercial, Integer idCategoria) {
		String queryStr = getQueryBase(ConsultaGananciaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":fechaInicial", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":fechaFinal", "'" + fechaFinal + "'");
		queryStr = queryStr.replaceAll("and facturas.act_comercial", " and facturas.act_comercial in ('" + actividadComercial + "') ");
		if (cliente != null) {
			queryStr = queryStr.replaceAll("and facturas.cliente_id", " and facturas.cliente_id =" + cliente.getId().toString() + " ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.cliente_id", " ");
		}
		if (idCategoria > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll( "and categorias.id =", " and categorias.id =" + idCategoria.toString() + " ");
			;
		} else {
			queryStr = queryStr.replaceAll("and categorias.id =", "");
		}
		if (estado > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("facturas.estado in", " and facturas.estado in (" + estado + ") ");
		} else {
			queryStr = queryStr.replaceAll("facturas.estado in", " and facturas.estado in (" + "2,6,7,5" + ") ");
		}

		Query query = entityManager.createNativeQuery(queryStr, ConsultaGananciaNative.class);
		return (Collection<ConsultaGananciaNative>) query.getResultList();
	}

	@Override
	public Collection<ListarFacturasImpuestoServicioNativa> findByFacturasImpuestoServicio(Empresa empresa, Integer idUsuario, Integer estado, String fechaInicial, String fechaFinal, String actividadComercial) {
		String queryStr = getQueryBase(ListarFacturasImpuestoServicioNativa.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());

		queryStr = queryStr.replaceAll(":fechaInicial", "'" + fechaInicial + "'");
		queryStr = queryStr.replaceAll(":fechaFinal", "'" + fechaFinal + "'");
		queryStr = queryStr.replaceAll("and facturas.act_comercial", " and facturas.act_comercial in ('" + actividadComercial + "') ");

		if (estado > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("and facturas.estado", " and facturas.estado in (" + estado + ") ");
		} else {
			queryStr = queryStr.replaceAll("and facturas.estado", " and facturas.estado in (" + "2,6,7,5" + ") ");
		}

		Query query = entityManager.createNativeQuery(queryStr, ListarFacturasImpuestoServicioNativa.class);
		return (Collection<ListarFacturasImpuestoServicioNativa>) query.getResultList();

	}

}
