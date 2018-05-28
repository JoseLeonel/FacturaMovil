package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface ClienteDao {

	void agregar(Cliente cliente);

	void modificar(Cliente cliente);

	void eliminar(Cliente cliente);

	Cliente buscar(Integer id);

	Cliente buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Cliente buscarPorCedulaYEmpresa(String cedula, Empresa empresa);
}