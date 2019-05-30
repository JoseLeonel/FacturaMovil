package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.Usuario;

public interface EmpresaBo {

	void agregar(Empresa empresa);

	void modificar(Empresa empresa);

	void eliminar(Empresa empresa);

	Empresa buscar(Integer id);

	Empresa buscarPorNombre(String nombre);

	Empresa buscarPorNombreComercial(String nombreComercial);

	Empresa buscarPorCedula(String cedula);
	
	String generarConsecutivoFactura(Empresa empresa,Usuario usuario,Factura factura)throws Exception;
	
	String generarConsecutivoRecepcionFactura(Empresa empresa, Usuario usuario, RecepcionFactura recepcionFactura) throws Exception;
	String generaClaveFacturaTributacion(Empresa empresa, String consecutivoFactura, Integer comprobanteElectronico) throws Exception;
	String generarConsecutivoProforma(Empresa empresa, Usuario usuario) throws Exception;
	Collection<Empresa> findByEstado(String estado);

}