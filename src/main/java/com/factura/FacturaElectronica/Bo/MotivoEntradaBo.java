package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.MotivoEntrada;

public interface MotivoEntradaBo {

	void agregar(MotivoEntrada motivoEntrada);

	void modificar(MotivoEntrada motivoEntrada);

	void eliminar(MotivoEntrada motivoEntrada);

	MotivoEntrada buscar(Integer id);

	MotivoEntrada buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}