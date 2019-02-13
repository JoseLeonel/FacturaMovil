package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.DetalleCompraBo;
import com.emprendesoftcr.Dao.DetalleCompraDao;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;

@EnableTransactionManagement
@Service("detalleCompraBo")
public class DetalleCompraBoImpl implements DetalleCompraBo {

	@Autowired
	private DetalleCompraDao	detalleCompraDao;

	
	@Transactional
	@Override
	public void agregar(DetalleCompra detalleCompra) {
		detalleCompraDao.agregar(detalleCompra);

	}

	@Transactional
	@Override
	public void modificar(DetalleCompra detalleCompra) {
		detalleCompraDao.modificar(detalleCompra);
	}

	@Transactional
	@Override
	public void eliminar(DetalleCompra detalleCompra) {
		detalleCompraDao.eliminar(detalleCompra);

	}

	
	@Override
	public Collection<DetalleCompra> findByCompra(Compra compra){
		return detalleCompraDao.findByCompra(compra);
	}

	
}
