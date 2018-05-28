package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Empresa;


public interface EmpresaDao {

	void agregar(Empresa empresa);

	void modificar(Empresa empresa);

	void eliminar(Empresa empresa);

	
	 Empresa buscar(Integer id);
	 Empresa buscarPorNombre(String nombre);
	 Empresa buscarPorNombreComercial(String nombreComercial);
	 Empresa buscarPorCedula(String cedula);

	

}