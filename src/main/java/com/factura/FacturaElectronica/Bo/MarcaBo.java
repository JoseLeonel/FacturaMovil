package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Marca;

public interface MarcaBo {

	void agregar(Marca marca);

	void modificar(Marca marca);

	void eliminar(Marca marca);

	Marca buscar(Integer id);

	Marca buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}