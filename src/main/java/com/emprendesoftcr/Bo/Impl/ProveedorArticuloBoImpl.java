package com.emprendesoftcr.Bo.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ProveedorArticuloBo;
import com.emprendesoftcr.Dao.ProveedorArticuloDao;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

@EnableTransactionManagement
@Service("proveedorArticuloBo")
public class ProveedorArticuloBoImpl implements ProveedorArticuloBo {

	@Autowired
	ProveedorArticuloDao proveedorArticuloDao;

	@Transactional
	@Override
	public void agregar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.agregar(proveedorArticulo);

	}

	@Transactional
	@Override
	public void modificar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.modificar(proveedorArticulo);
	}

	@Override
	@Transactional
	public void eliminar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.eliminar(proveedorArticulo);
	}

	@Override
	public ProveedorArticulo findById(Long id) {

		return proveedorArticuloDao.findById(id);
	}

	public ProveedorArticulo findByCodigo(String codigo, Proveedor proveedor) {
		return proveedorArticuloDao.findByCodigo(codigo, proveedor);
	}

	@Override
	public List<Map<String, Object>> articuloPorProveedor(Integer idProveedor) {
		return proveedorArticuloDao.articuloPorProveedor(idProveedor);
	}

	@Override
	public List<Map<String, Object>> articuloPorProveedor(Integer idProveedor, String codigo, Integer idEmpresa) {
		
		return proveedorArticuloDao.articuloPorProveedor(idProveedor, codigo, idEmpresa);
	}

	@Override
	public List<Map<String, Object>> articuloCantidadVendido(String idCodigo, Integer idEmpresa, String fechaInicial, String fechaFinal) {
		
		return proveedorArticuloDao.articuloCantidadVendido(idCodigo, idEmpresa, fechaInicial, fechaFinal);
	}

}
