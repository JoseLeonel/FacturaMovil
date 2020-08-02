package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.DetalleCompraSimplificadaBo;
import com.emprendesoftcr.Dao.DetalleCompraSimplificadaDao;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;

@EnableTransactionManagement
@Service("detalleCompraSimplificadaBo")
public class DetalleCompraSimplificadaBoImpl implements DetalleCompraSimplificadaBo {

	@Autowired
	private DetalleCompraSimplificadaDao	detalleCompraSimplificadaDao;

	private Logger			log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(DetalleCompraSimplificada detalleCompraSimplificada) {
		detalleCompraSimplificadaDao.agregar(detalleCompraSimplificada);

	}

	@Transactional
	@Override
	public void modificar(DetalleCompraSimplificada detalleCompraSimplificada) {
		detalleCompraSimplificadaDao.modificar(detalleCompraSimplificada);
	}

	@Transactional
	@Override
	public void eliminar(DetalleCompraSimplificada detalleCompraSimplificada) {
		detalleCompraSimplificadaDao.eliminar(detalleCompraSimplificada);

	}

	@Transactional
	@Override
	public Integer eliminarDetalleFactura(CompraSimplificada compraSimplificada) throws Exception {
		try {
			return detalleCompraSimplificadaDao.eliminarDetalleFactura(compraSimplificada);
		} catch (Exception e) {
			log.info("** Error  eliminarDetalleFactura: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}

	}

	
	
	

	@Override
	public DetalleCompraSimplificada findByCodigoAndEmpresa(String codigo , Empresa empresa) {
		return detalleCompraSimplificadaDao.findByCodigoAndEmpresa(codigo, empresa);
	}
	
	@Override
	public Collection<DetalleCompraSimplificada> findByCompraSimplificada(CompraSimplificada compraSimplificada){
		return detalleCompraSimplificadaDao.findByCompraSimplificada(compraSimplificada);
	}
}
