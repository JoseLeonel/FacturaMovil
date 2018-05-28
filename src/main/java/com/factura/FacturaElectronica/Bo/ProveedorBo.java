package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Proveedor;

public interface ProveedorBo {

	void agregar(Proveedor proveedor);

	void modificar(Proveedor proveedor);

	void eliminar(Proveedor proveedor);

	Proveedor buscar(Integer id);

	Proveedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Proveedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa);
}