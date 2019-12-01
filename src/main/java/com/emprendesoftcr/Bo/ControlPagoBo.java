package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;

public interface ControlPagoBo {
	void agregar(ControlPago controlPago);

	void modificar(ControlPago controlPago);

	void eliminar(ControlPago controlPago);

	ControlPago buscar(Long id);

	ControlPago findByEstadoAndEmpresa(Integer estado, Empresa empresa);

}
