package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.modelo.Usuario;

public interface EmpresaBo {

	void agregar(Empresa empresa);

	void modificar(Empresa empresa);

	void eliminar(Empresa empresa);

	Empresa buscar(Integer id);

	Empresa buscarPorNombre(String nombre);

	Empresa buscarPorNombreComercial(String nombreComercial);

	Empresa buscarPorCedula(String cedula);
	
	String generarConsecutivoFactura(Empresa empresa,Usuario usuario,Factura factura)throws Exception;
	
	

}