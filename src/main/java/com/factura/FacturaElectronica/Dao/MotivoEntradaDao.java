package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.MotivoEntrada;

public interface MotivoEntradaDao {

	void agregar(MotivoEntrada motivoEntrada);

	void modificar(MotivoEntrada motivoEntrada);

	void eliminar(MotivoEntrada motivoEntrada);

	MotivoEntrada buscar(Integer id);

	MotivoEntrada buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}