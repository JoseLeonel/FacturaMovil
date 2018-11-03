package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ComandaBo;
import com.emprendesoftcr.Dao.ComandaDao;
import com.emprendesoftcr.modelo.ComandaMesa;

@EnableTransactionManagement
@Service("comandaBo")
public class ComandaBoImpl implements ComandaBo {

	@Autowired
	ComandaDao comandaDao;

	@Transactional
	@Override
	public void agregar(ComandaMesa comandaMesa) {
		comandaDao.agregar(comandaMesa);
	}

	@Transactional
	@Override
	public void modificar(ComandaMesa comandaMesa) {
		comandaDao.modificar(comandaMesa);
	}

	@Transactional
	@Override
	public void eliminar(ComandaMesa comandaMesa) {
		comandaDao.eliminar(comandaMesa);
	}

	@Transactional
	@Override
	public void actualizarComandaMesaFacturas(Long idMesa) {
		comandaDao.actualizarComandaMesaFacturas(idMesa);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public ComandaMesa buscar(Long id) {
		return comandaDao.buscar(id);
	}

	@Override
	public Collection<ComandaMesa> buscarPorFacturaMesa(Long idFactura, Long idMesa) {
		return comandaDao.buscarPorFacturaMesa(idFactura, idMesa);
	}


}