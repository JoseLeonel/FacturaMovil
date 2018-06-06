package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.EmpresaDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Empresa;

/**
 * Empresa es todas las que facturan EmpresaDaoImpl.
 * @author jose.
 * @since 20 abr. 2018
 */
@Repository("empresaDao")
public class EmpresaDaoImpl implements EmpresaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Empresa empresa) {
		entityManager.persist(empresa);
	}

	public void modificar(Empresa empresa) {
		entityManager.merge(empresa);
	}

	public void eliminar(Empresa empresa) {
		entityManager.remove(empresa);
	}

	/**
	 * Buscar por ID
	 * @see com.factura.dao.EmpresaDao#buscar(java.lang.Integer)
	 */
	public Empresa buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Empresa obj where obj.id = :id");
		query.setParameter("id", id);
		List<Empresa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Empresa) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por nombre
	 * @param nombre
	 * @return
	 */
	public Empresa buscarPorNombre(String nombre) {
		Query query = entityManager.createQuery("select obj from Empresa obj where obj.nombre = :nombre");
		query.setParameter("nombre", nombre);
		List<Empresa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Empresa) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por nombre comercial
	 * @param nombreComercial
	 * @return
	 */
	public Empresa buscarPorNombreComercial(String nombreComercial) {
		Query query = entityManager.createQuery("select obj from Empresa obj where obj.nombreComercial = :nombreComercial");
		query.setParameter("nombreComercial", nombreComercial);
		List<Empresa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Empresa) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por cedula
	 * @param cedula
	 * @return
	 */
	public Empresa buscarPorCedula(String cedula) {
		Query query = entityManager.createQuery("select obj from Empresa obj where obj.cedula = :cedula");
		query.setParameter("cedula", cedula);
		List<Empresa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Empresa) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Genera el consecutvio de la Factura de empresa
	 * @see com.factura.FacturaElectronica.Dao.EmpresaDao#generarConsecutivoFactura(com.factura.FacturaElectronica.modelo.Empresa)
	 */
	@Override
	public Double generarConsecutivoFactura(Empresa empresa) {
		Double consecutivo = Constantes.ZEROS_DOUBLE;
		consecutivo = empresa.getNumeroConsecutivo() + 1;
		empresa.setNumeroConsecutivo(consecutivo);
		modificar(empresa);

		return consecutivo;

	}

}