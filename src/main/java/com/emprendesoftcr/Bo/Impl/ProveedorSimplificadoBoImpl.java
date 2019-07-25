package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.ProveedorSimplificadoBo;
import com.emprendesoftcr.Dao.ProveedorSimplificadoDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;

@EnableTransactionManagement
@Service("proveedorSimplificadoBo")
public class ProveedorSimplificadoBoImpl implements ProveedorSimplificadoBo {

	@Autowired
	private ProveedorSimplificadoDao proveedorSimplificadoDao;

	@Override
	public void agregar(ProveedorSimplificado proveedorSimplificado) {
		proveedorSimplificadoDao.agregar(proveedorSimplificado);
	}

	@Override
	public void modificar(ProveedorSimplificado proveedorSimplificado) {
		proveedorSimplificadoDao.modificar(proveedorSimplificado);

	}

	@Override
	public void eliminar(ProveedorSimplificado proveedorSimplificado) {
		proveedorSimplificadoDao.eliminar(proveedorSimplificado);

	}

	@Override
	public ProveedorSimplificado buscar(Long id) {

		return proveedorSimplificadoDao.buscar(id);
	}

	@Override
	public ProveedorSimplificado buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {

		return proveedorSimplificadoDao.buscarPorNombreCompletoYEmpresa(nombreCompleto, empresa);
	}

	@Override
	public ProveedorSimplificado buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {

		return proveedorSimplificadoDao.buscarPorCedulaYEmpresa(cedula, empresa);
	}

}
