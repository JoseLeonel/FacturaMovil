package com.factura.FacturaElectronica.Dao.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.EmpresaDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.fisco.FacturaElectronicaUtils;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.modelo.Usuario;

/**
 * Empresa es todas las que facturan EmpresaDaoImpl.
 * @author jose.
 * @since 20 abr. 2018
 */
@Repository("empresaDao")
public class EmpresaDaoImpl implements EmpresaDao {

	@PersistenceContext
	EntityManager entityManager;
	
	private Logger				log	= LoggerFactory.getLogger(this.getClass());

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
	public String generarConsecutivoFactura(Empresa empresa, Usuario usuario,Factura factura)  throws Exception{
    String resultado = Constantes.EMPTY;
		try {
			Integer consecutivo = Constantes.ZEROS;
			consecutivo = empresa.getNumeroConsecutivo() == null ? Constantes.CONSECUTIVO_INICIAL_FACTURA : empresa.getNumeroConsecutivo() + 1;
			empresa.setNumeroConsecutivo(consecutivo);
			modificar(empresa);
			// Casa matriz
			String casaMatriz = Constantes.EMPTY;
			casaMatriz = empresa.getCazaMatriz() == null ? Constantes.CASA_MATRIZ_INICIAL_FACTURA : empresa.getCazaMatriz();
			// Terminal donde esta vendiendo el usaurio
			String terminalUsuario = Constantes.EMPTY;
			terminalUsuario = usuario.getTerminalFactura() == null ? Constantes.TERMINAL_INICIAL_FACTURA : usuario.getTerminalFactura();
			String consecutivoFactura = "0000000000".substring(consecutivo.toString().length()) + consecutivo;

			resultado = casaMatriz + terminalUsuario +factura.getTipoDoc()+ consecutivoFactura;
			
		} catch (Exception e) {
			log.info("** Error  generarConsecutivoFactura: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return resultado;
	}

	/**
	 * Clave de la factura para tributacion
	 * @see com.factura.FacturaElectronica.Dao.EmpresaDao#generaClaveFacturaTributacion(com.factura.FacturaElectronica.modelo.Empresa, java.lang.String, java.lang.Integer)
	 */
	@Override
	public String generaClaveFacturaTributacion(Empresa empresa,String consecutivoFactura,Integer comprobanteElectronico) throws Exception{
		
		String resultado = Constantes.EMPTY;
		try {
			resultado = FacturaElectronicaUtils.claveFactura(empresa.getCedula(), consecutivoFactura, comprobanteElectronico, empresa.getClave());
			
		} catch (Exception e) {
			log.info("** Error  generarClaveFacturaTributaria: " + e.getMessage() + " fecha " + new Date());
			throw e;
			
		}
		
		return resultado;
	}
	
}