package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoSalida;

public interface MotivoSalidaDao {

	void agregar(MotivoSalida motivoSalida);

	void modificar(MotivoSalida motivoSalida);

	void eliminar(MotivoSalida motivoSalida);

	MotivoSalida buscar(Long id);

	MotivoSalida buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}