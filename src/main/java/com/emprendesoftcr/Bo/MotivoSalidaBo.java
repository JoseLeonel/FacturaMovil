package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoSalida;

public interface MotivoSalidaBo {

	void agregar(MotivoSalida motivoSalida);

	void modificar(MotivoSalida motivoSalida);

	void eliminar(MotivoSalida motivoSalida);

	MotivoSalida buscar(Long id);

	MotivoSalida buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}