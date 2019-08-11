package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ConsultasNativeDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.sqlNativo.BaseNativeQuery;
import com.emprendesoftcr.modelo.sqlNativo.CompraSimplificadaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaComprobarNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
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
	public Collection<HaciendaNative> findByEmpresaAndEstado(Empresa empresa, String fechaInicial , String fechaFinal) {
		String queryStr = getQueryBase(HaciendaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":fechaInicial","'"+ fechaInicial+"'");
		queryStr = queryStr.replaceAll(":fechaFinal","'"+ fechaFinal+"'");
		Query query = entityManager.createNativeQuery(queryStr, HaciendaNative.class);
		return (Collection<HaciendaNative>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<HaciendaNativeByEmpresaAndFechaAndCliente> findByEmpresaAndFechaAndCliente(Empresa empresa, String fechaInicial , String fechaFinal,String cedula){
		String queryStr = getQueryBase(HaciendaNativeByEmpresaAndFechaAndCliente.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":fechaInicial","'"+ fechaInicial+"'");
		queryStr = queryStr.replaceAll(":fechaFinal","'"+ fechaFinal+"'");
		queryStr = queryStr.replaceAll(":CEDULA", cedula.toString());
		Query query = entityManager.createNativeQuery(queryStr, HaciendaNativeByEmpresaAndFechaAndCliente.class);
		return (Collection<HaciendaNativeByEmpresaAndFechaAndCliente>) query.getResultList();
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndEstadoAndUsuario> findByProformasByEmpresaAndEstadoAndUsuario(Empresa empresa, Integer estado,Integer idUsuario){
		String queryStr = getQueryBase(ProformasByEmpresaAndEstadoAndUsuario.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":ESTADO","'"+ estado+"'");
		queryStr = queryStr.replaceAll(":IDUSUARIO","'"+ idUsuario+"'");
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndEstadoAndUsuario.class);
		return (Collection<ProformasByEmpresaAndEstadoAndUsuario>) query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndFacturadaAndUsuario> findByProformasByEmpresaFacturadaAndUsuario(Empresa empresa, Integer idUsuario){
		String queryStr = getQueryBase(ProformasByEmpresaAndFacturadaAndUsuario.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":IDUSUARIO","'"+ idUsuario+"'");
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndFacturadaAndUsuario.class);
		return (Collection<ProformasByEmpresaAndFacturadaAndUsuario>) query.getResultList();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndEstado> findByProformasByEmpresaAndEstado(Empresa empresa, Integer estado){
		String queryStr = getQueryBase(ProformasByEmpresaAndEstado.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":ESTADO","'"+ estado+"'");
		
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndEstado.class);
		return (Collection<ProformasByEmpresaAndEstado>) query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndFacturada> findByProformasByEmpresaFacturada(Empresa empresa){
		String queryStr = getQueryBase(ProformasByEmpresaAndFacturada.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndFacturada.class);
		return (Collection<ProformasByEmpresaAndFacturada>) query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FacturasDelDiaNative> findByFacturasDelDia(Empresa empresa,Integer idusuario,String estado,String fecha){
		String queryStr = getQueryBase(FacturasDelDiaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		if (idusuario > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("facturas.usuario_id =" ," facturas.usuario_id ='"+ idusuario.toString() + "' ");	
		}else {
			queryStr = queryStr.replaceAll("and facturas.usuario_id =" ," ");
		}
		
		queryStr = queryStr.replaceAll(":ESTADO", estado.toString());
		queryStr = queryStr.replaceAll(":FECHA", "'"+fecha+"'");	
		
		Query query = entityManager.createNativeQuery(queryStr, FacturasDelDiaNative.class);
		return (Collection<FacturasDelDiaNative>) query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<FacturasSinNotaCreditoNative> findByFacturasAnulacion(Empresa empresa,Integer idusuario,String estado,String fechaInicial,String fechaFinal,Long idCliente){
		String queryStr = getQueryBase(FacturasSinNotaCreditoNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":ESTADO", estado.toString());
		queryStr = queryStr.replaceAll(":FECHAINICIAL","'"+ fechaInicial+"'");
		queryStr = queryStr.replaceAll(":FECHAFINAL","'"+ fechaFinal+"'");
		if (idusuario > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("facturas.usuario_id =" ," facturas.usuario_id ='"+ idusuario.toString() + "' ");	
		}else {
			queryStr = queryStr.replaceAll("and facturas.usuario_id =" ," ");
		}
		if (idCliente > Constantes.ZEROS_LONG) {
			queryStr = queryStr.replaceAll("and facturas.cliente_id =" ," and facturas.cliente_id ='"+ idCliente.toString() + "' ");	
		}else {
			queryStr = queryStr.replaceAll("and facturas.cliente_id = " ," ");
		}
		
		Query query = entityManager.createNativeQuery(queryStr, FacturasSinNotaCreditoNative.class);
		return (Collection<FacturasSinNotaCreditoNative>) query.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CompraSimplificadaNative> findComprasSimplificadasByFechaAndEstadoAndEmpresa(Empresa empresa, String fechaInicial , String fechaFinal,Long idProveedor,Integer estado,Integer idUsuario){
		String queryStr = getQueryBase(CompraSimplificadaNative.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		queryStr = queryStr.replaceAll(":Estado", estado.toString());
		queryStr = queryStr.replaceAll(":FECHAINICIAL","'"+ fechaInicial+"'");
		queryStr = queryStr.replaceAll(":FechaFinal","'"+ fechaFinal+"'");
		if (idUsuario > Constantes.ZEROS) {
			queryStr = queryStr.replaceAll("compra_simpli.usuario_id =" ," compra_simpli.usuario_id ='"+ idUsuario.toString() + "' ");	
		}else {
			queryStr = queryStr.replaceAll("and compra_simpli.usuario_id =" ," ");
		}
		if (idProveedor > Constantes.ZEROS_LONG) {
			queryStr = queryStr.replaceAll("and compra_simpli.proveedorsimpl_id =" ," and compra_simpli.proveedorsimpl_id ='"+ idProveedor.toString() + "' ");	
		}else {
			queryStr = queryStr.replaceAll("and compra_simpli.proveedorsimpl_id = " ," ");
		}
		
		Query query = entityManager.createNativeQuery(queryStr, CompraSimplificadaNative.class);
		return (Collection<CompraSimplificadaNative>) query.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<HaciendaComprobarNative> findByComprabarDocumentoPendienteaceptar(){
		String queryStr = getQueryBase(HaciendaComprobarNative.class);
		Query query = entityManager.createNativeQuery(queryStr, HaciendaComprobarNative.class);
		return (Collection<HaciendaComprobarNative>) query.getResultList();
		
	}
	private static <T> String getQueryBase(Class<T> claseObjecto) {
		return ((claseObjecto).getDeclaredAnnotationsByType(BaseNativeQuery.class))[0].query();
	}
	
}
