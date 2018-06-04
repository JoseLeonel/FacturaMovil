package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;

public interface FacturaBo {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Integer id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);


}