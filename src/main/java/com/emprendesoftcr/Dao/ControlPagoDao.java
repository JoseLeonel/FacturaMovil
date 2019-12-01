package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;

public interface ControlPagoDao {

	void agregar(ControlPago controlPago);

	void modificar(ControlPago controlPago);

	void eliminar(ControlPago controlPago);

	ControlPago buscar(Long id);

	ControlPago findByEstadoAndEmpresa(Integer estado, Empresa empresa);

	

}