package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ConsultasNativeDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.sqlNativo.BaseNativeQuery;
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
		
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndEstadoAndUsuario.class);
		return (Collection<ProformasByEmpresaAndEstado>) query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ProformasByEmpresaAndFacturada> findByProformasByEmpresaFacturada(Empresa empresa){
		String queryStr = getQueryBase(ProformasByEmpresaAndFacturada.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresa.getId().toString());
		
		Query query = entityManager.createNativeQuery(queryStr, ProformasByEmpresaAndFacturadaAndUsuario.class);
		return (Collection<ProformasByEmpresaAndFacturada>) query.getResultList();
	}
	private static <T> String getQueryBase(Class<T> claseObjecto) {
		return ((claseObjecto).getDeclaredAnnotationsByType(BaseNativeQuery.class))[0].query();
	}
	
}
