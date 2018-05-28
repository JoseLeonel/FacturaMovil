package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.MotivoEntradaBo;
import com.factura.FacturaElectronica.Dao.MotivoEntradaDao;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.MotivoEntrada;

/**
 * motivo de entradas que se registran en el kardex MotivoEntradaBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Transactional
@EnableTransactionManagement
@Service
public class MotivoEntradaBoImpl implements MotivoEntradaBo {

	@Autowired
	MotivoEntradaDao motivoEntradaDao;

	public void agregar(MotivoEntrada motivoEntrada) {
		motivoEntradaDao.agregar(motivoEntrada);
	}

	public void modificar(MotivoEntrada motivoEntrada) {
		motivoEntradaDao.modificar(motivoEntrada);
	}

	public void eliminar(MotivoEntrada motivoEntrada) {
		motivoEntradaDao.eliminar(motivoEntrada);
	}

	/**
	 * Buscar motivo de entrada por descripcion y empresa
	 * @see com.factura.bo.MotivoEntradaBo#buscarByDescripcionAndSucursal(java.lang.String, com.factura.domain.Sucursal)
	 */
	@Override
	public MotivoEntrada buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return motivoEntradaDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	/**
	 * Buscar un motivo de entrada
	 * @see com.factura.bo.MotivoEntradaBo#buscar(java.lang.Integer)
	 */
	@Override
	public MotivoEntrada buscar(Integer id) {
		return motivoEntradaDao.buscar(id);
	}

}