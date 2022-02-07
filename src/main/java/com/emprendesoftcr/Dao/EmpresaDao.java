package com.emprendesoftcr.Dao;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.Usuario;

@Repository("empresaDao")
public interface EmpresaDao {

	void agregar(Empresa empresa);

	void modificar(Empresa empresa);

	void eliminar(Empresa empresa);

	Empresa buscar(Integer id);

	Empresa buscarPorNombre(String nombre);

	Empresa buscarPorNombreComercial(String nombreComercial);

	Empresa buscarPorCedula(String cedula);

	String generarConsecutivoFactura(Empresa empresa, Usuario usuario, Factura factura) throws Exception;
	
	String spGenerarConsecutivoFactura(Empresa empresa, Usuario usuario, String tipoDoc) throws Exception;
	
	String generarConsecutivoProforma(Empresa empresa, Usuario usuario) throws Exception;
	
	String generarConsecutivoNotaCreditoInterno(Empresa empresa, Usuario usuario) throws Exception;
	
	String generarConsecutivoNotaDebitoInterno(Empresa empresa, Usuario usuario) throws Exception;
	
	String generarConsecutivoAjusteInventario(Empresa empresa, Usuario usuario) throws Exception;

	String generarConsecutivoRecepcionFactura(Empresa empresa, Usuario usuario, RecepcionFactura recepcionFactura) throws Exception;

	String generaClaveFacturaTributacion(Empresa empresa, String consecutivoFactura, Integer comprobanteElectronico) throws Exception;
	
	Collection<Empresa> findByEstado(String estado);
	
	String generarConsecutivoCompraSimplificada(Empresa empresa, Usuario usuario) throws Exception;

}