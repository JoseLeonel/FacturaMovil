package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.MotivoSalida;

public interface MotivoSalidaDao {

	void agregar(MotivoSalida motivoSalida);

	void modificar(MotivoSalida motivoSalida);

	void eliminar(MotivoSalida motivoSalida);

	MotivoSalida buscar(Integer id);

	MotivoSalida buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}