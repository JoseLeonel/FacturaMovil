package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Categoria;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface CategoriaBo {

	void agregar(Categoria categoria);

	void modificar(Categoria categoria);

	void eliminar(Categoria categoria);

	Categoria buscar(Integer id);

	Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}