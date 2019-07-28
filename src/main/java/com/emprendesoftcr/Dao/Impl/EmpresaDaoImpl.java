package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.EmpresaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Empresa es todas las que facturan EmpresaDaoImpl.
 * @author jose.
 * @since 20 abr. 2018
 */
@Repository("empresaDao")
public class EmpresaDaoImpl implements EmpresaDao {

	@PersistenceContext
	EntityManager		entityManager;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(Empresa empresa) {
		entityManager.persist(empresa);
	}

	@Override
	public void modificar(Empresa empresa) {
		entityManager.merge(empresa);
	}

	@Override
	public void eliminar(Empresa empresa) {
		entityManager.remove(empresa);
	}

	/**
	 * Buscar por ID
	 * @see com.factura.dao.EmpresaDao#buscar(java.lang.Integer)
	 */
	@Override
	public Empresa buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Empresa obj where obj.id = :id");
		query.setParameter("id", id);
		return (Empresa) query.getSingleResult();
	}

	public Empresa buscar2(Integer id) {
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
	@Override
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
	@Override
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
	@Override
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
	 * @see com.emprendesoftcr.Dao.EmpresaDao#generarConsecutivoFactura(com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public String generarConsecutivoFactura(Empresa empresa, Usuario usuario, Factura factura) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			Integer consecutivo = Constantes.ZEROS;
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				consecutivo = empresa.getTiqueteConsecutivo();
				empresa.setTiqueteConsecutivo(empresa.getTiqueteConsecutivo() + 1);

			}
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {

				consecutivo = empresa.getNumeroConsecutivo();
				empresa.setNumeroConsecutivo(empresa.getNumeroConsecutivo() + 1);

			}
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				consecutivo = empresa.getNotacConsecutivo();
				empresa.setNotacConsecutivo(empresa.getNotacConsecutivo() + 1);
			}
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				consecutivo = empresa.getNotadConsecutivo();
				empresa.setNotadConsecutivo(empresa.getNotadConsecutivo() + 1);
			}

			modificar(empresa);
			// Casa matriz
			String casaMatriz = Constantes.EMPTY;
			casaMatriz = empresa.getCazaMatriz() == null ? Constantes.CASA_MATRIZ_INICIAL_FACTURA : empresa.getCazaMatriz();
			// Terminal donde esta vendiendo el usaurio
			String terminalUsuario = Constantes.EMPTY;
			terminalUsuario = usuario.getTerminalFactura() == null ? Constantes.TERMINAL_INICIAL_FACTURA : FacturaElectronicaUtils.replazarConZeros(usuario.getTerminalFactura(), "00000");
			String consecutivoFactura = "0000000000".substring(consecutivo.toString().length()) + consecutivo;

			resultado = casaMatriz + terminalUsuario + factura.getTipoDoc() + consecutivoFactura;

		} catch (Exception e) {
			log.info("** Error  generarConsecutivoFactura: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return resultado;
	}

	
	@Override
	public String generarConsecutivoCompraSimplificada(Empresa empresa, Usuario usuario) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			Integer consecutivo = Constantes.ZEROS;
		  consecutivo = empresa.getConsecutivoCompraSimplificada();
		  empresa.setConsecutivoCompraSimplificada(empresa.getConsecutivoCompraSimplificada()+1);

			modificar(empresa);
			// Casa matriz
			String casaMatriz = Constantes.EMPTY;
			casaMatriz = empresa.getCazaMatriz() == null ? Constantes.CASA_MATRIZ_INICIAL_FACTURA : empresa.getCazaMatriz();
			// Terminal donde esta vendiendo el usaurio
			String terminalUsuario = Constantes.EMPTY;
			terminalUsuario = usuario.getTerminalFactura() == null ? Constantes.TERMINAL_INICIAL_FACTURA : FacturaElectronicaUtils.replazarConZeros(usuario.getTerminalFactura(), "00000");
			String consecutivoFactura = "0000000000".substring(consecutivo.toString().length()) + consecutivo;

			resultado = casaMatriz + terminalUsuario + Constantes.FACTURA_TIPO_DOC_COMPRA_SIMPLIFICADA + consecutivoFactura;

		} catch (Exception e) {
			log.info("** Error  generarConsecutivoCompraSimplificada: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return resultado;
	}

	
	/**
	 * Genera el consecutvio de la Factura de empresa
	 * @see com.emprendesoftcr.Dao.EmpresaDao#generarConsecutivoFactura(com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public String generarConsecutivoRecepcionFactura(Empresa empresa, Usuario usuario, RecepcionFactura recepcionFactura) throws Exception {
		String resultado = Constantes.EMPTY;
		String tipoDoc = "00";
		try {
			Integer consecutivo = Constantes.ZEROS;
			if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO)) {
				consecutivo = empresa.getAceptadoConsecutivo();
				consecutivo = consecutivo == null ? 1 : consecutivo;
				empresa.setAceptadoConsecutivo(consecutivo + 1);
				tipoDoc = Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO;
			}
			if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO_PARCIAL)) {
				consecutivo = empresa.getAceptadoParcialConsecutivo();
				consecutivo = consecutivo == null ? 1 : consecutivo;
				empresa.setAceptadoParcialConsecutivo(consecutivo + 1);
				tipoDoc = Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL;
			}
			if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_RECHAZADO)) {
				consecutivo = empresa.getRechazadoConsecutivo();
				consecutivo = consecutivo == null ? 1 : consecutivo;
				empresa.setRechazadoConsecutivo(consecutivo + 1);
				tipoDoc = Constantes.RECEPCION_FACTURA_TIPO_DOC_RECHAZADO;
			}
			modificar(empresa);

			// Casa matriz
			String casaMatriz = Constantes.EMPTY;
			casaMatriz = empresa.getCazaMatriz() == null ? Constantes.CASA_MATRIZ_INICIAL_FACTURA : empresa.getCazaMatriz();

			// Terminal donde esta vendiendo el usaurio
			String terminalUsuario = Constantes.EMPTY;
			terminalUsuario = usuario.getTerminalFactura() == null ? Constantes.TERMINAL_INICIAL_FACTURA : FacturaElectronicaUtils.replazarConZeros(usuario.getTerminalFactura(), "00000");
			String consecutivoFactura = "0000000000".substring(consecutivo.toString().length()) + consecutivo;
			resultado = casaMatriz + terminalUsuario + tipoDoc + consecutivoFactura;
		} catch (Exception e) {
			log.info("** Error  generarConsecutivoFactura: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	/**
	 * Clave de la factura para tributacion
	 * @see com.emprendesoftcr.Dao.EmpresaDao#generaClaveFacturaTributacion(com.emprendesoftcr.modelo.Empresa, java.lang.String, java.lang.Integer)
	 */
	@Override
	public String generaClaveFacturaTributacion(Empresa empresa, String consecutivoFactura, Integer comprobanteElectronico) throws Exception {

		String resultado = Constantes.EMPTY;
		try {
			Random rnd = new Random();
			int dig3 = rnd.nextInt(900) + 100; // siempre 3 digitos
			int dig5 = rnd.nextInt(90000) + 10000; // siempre 5 digitos
			Integer valor3 = dig3;
			Integer valor5 = dig5;
			String codigoSeguridad = valor3.toString() + valor5.toString();
			resultado = FacturaElectronicaUtils.claveFactura(empresa.getCedula(), consecutivoFactura, comprobanteElectronico, codigoSeguridad);

		} catch (Exception e) {
			log.info("** Error  generarClaveFacturaTributaria: " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

		return resultado;
	}

	/**
	 * Lista de empresas activas
	 * @see com.emprendesoftcr.Dao.EmpresaDao#findByEstado(java.lang.String)
	 */
	@Override
	public Collection<Empresa> findByEstado(String estado) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Empresa obj");
		hql.append(" where obj.estado = :estado ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("estado", estado);
		return query.getResultList();

	}

	/**
	 * Proforma Consecutivo
	 * @see com.emprendesoftcr.Dao.EmpresaDao#generarConsecutivoProforma(com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Usuario, com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public String generarConsecutivoProforma(Empresa empresa, Usuario usuario) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			Integer consecutivo = Constantes.ZEROS;
			consecutivo = empresa.getConsecutivoProforma();
			empresa.setConsecutivoProforma(empresa.getConsecutivoProforma() + 1);
			modificar(empresa);
			// Casa matriz
			String casaMatriz = Constantes.EMPTY;
			casaMatriz = empresa.getCazaMatriz() == null ? Constantes.CASA_MATRIZ_INICIAL_FACTURA : empresa.getCazaMatriz();
			// Terminal donde esta vendiendo el usaurio
			String terminalUsuario = Constantes.EMPTY;
			terminalUsuario = usuario.getTerminalFactura() == null ? Constantes.TERMINAL_INICIAL_FACTURA : FacturaElectronicaUtils.replazarConZeros(usuario.getTerminalFactura(), "00000");
			String consecutivoFactura = "0000000000".substring(consecutivo.toString().length()) + consecutivo;
			String tipoDoc = Constantes.FACTURA_TIPO_DOC_PROFORMAS;
			resultado = casaMatriz + terminalUsuario + tipoDoc + consecutivoFactura;

		} catch (Exception e) {
			log.info("** Error  generarConsecutivoProforma: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return resultado;
	}

}