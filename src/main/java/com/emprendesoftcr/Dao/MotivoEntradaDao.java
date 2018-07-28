package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoEntrada;

public interface MotivoEntradaDao {

	void agregar(MotivoEntrada motivoEntrada);

	void modificar(MotivoEntrada motivoEntrada);

	void eliminar(MotivoEntrada motivoEntrada);

	MotivoEntrada buscar(Long id);

	MotivoEntrada buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}