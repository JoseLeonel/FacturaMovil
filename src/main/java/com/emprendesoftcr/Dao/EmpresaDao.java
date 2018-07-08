package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;

public interface EmpresaDao {

	void agregar(Empresa empresa);

	void modificar(Empresa empresa);

	void eliminar(Empresa empresa);

	Empresa buscar(Integer id);

	Empresa buscarPorNombre(String nombre);

	Empresa buscarPorNombreComercial(String nombreComercial);

	Empresa buscarPorCedula(String cedula);

	String generarConsecutivoFactura(Empresa empresa, Usuario usuario, Factura factura) throws Exception;

	String generaClaveFacturaTributacion(Empresa empresa, String consecutivoFactura, Integer comprobanteElectronico) throws Exception;

}