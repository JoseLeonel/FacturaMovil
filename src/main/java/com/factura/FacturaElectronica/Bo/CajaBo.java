package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Caja;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface CajaBo {

	void agregar(Caja caja);

	void modificar(Caja caja);

	void eliminar(Caja caja);

	Caja buscar(Integer id);
	
	Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa);

	Caja findByTerminalAndEmpresa(String terminal, Empresa empresa);


}