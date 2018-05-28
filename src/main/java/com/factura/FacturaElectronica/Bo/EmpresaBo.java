package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Empresa;

public interface EmpresaBo {

	void agregar(Empresa empresa);

	void modificar(Empresa empresa);

	void eliminar(Empresa empresa);

	Empresa buscar(Integer id);

	Empresa buscarPorNombre(String nombre);

	Empresa buscarPorNombreComercial(String nombreComercial);

	Empresa buscarPorCedula(String cedula);

}