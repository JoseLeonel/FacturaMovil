package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.MotivoSalidaBo;
import com.emprendesoftcr.Dao.MotivoSalidaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoSalida;

/**
 * Motivo de salida para los registros del kardex MotivoSalidaBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@EnableTransactionManagement
@Service("motivoSalidaBo")
public class MotivoSalidaBoImpl implements MotivoSalidaBo {

	@Autowired
	MotivoSalidaDao motivoSalidaDao;

	@Override
	@Transactional
	public void agregar(MotivoSalida motivoSalida) {
		motivoSalidaDao.agregar(motivoSalida);
	}

	@Override
	@Transactional
	public void modificar(MotivoSalida motivoSalida) {
		motivoSalidaDao.modificar(motivoSalida);
	}

	@Override
	@Transactional
	public void eliminar(MotivoSalida motivoSalida) {
		motivoSalidaDao.eliminar(motivoSalida);
	}

	/**
	 * Buscar un motivo de salida por descripcion y sucursal
	 * @see com.factura.bo.MotivoSalidaBo#buscarByDescripcionAndSucursal(java.lang.String, com.factura.domain.Sucursal)
	 */
	@Override
	public MotivoSalida buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return motivoSalidaDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.MotivoSalidaBo#buscar(java.lang.Integer)
	 */
	@Override
	public MotivoSalida buscar(Long id) {
		return motivoSalidaDao.buscar(id);
	}

}