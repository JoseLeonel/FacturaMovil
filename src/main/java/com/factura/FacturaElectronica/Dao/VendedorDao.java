package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Vendedor;

public interface VendedorDao {

	void agregar(Vendedor vendedor);

	void modificar(Vendedor vendedor);

	void eliminar(Vendedor vendedor);

	Vendedor buscar(Integer id);

	Vendedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Vendedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa);

}