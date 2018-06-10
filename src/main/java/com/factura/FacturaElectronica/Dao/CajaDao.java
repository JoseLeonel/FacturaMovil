package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Caja;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface CajaDao {

	void agregar(Caja caja);

	void modificar(Caja caja);

	void eliminar(Caja caja);

	Caja buscar(Integer id);

	Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa);

	Caja findByTerminalAndEmpresa(String terminal, Empresa empresa);

}