package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;

@Transactional
@EnableTransactionManagement
@Service("recepcionFacturaBo")
public class RecepcionFacturaBoImpl implements RecepcionFacturaBo {

	@Autowired
	RecepcionFacturaDao						recepcionFacturaDao;
	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(RecepcionFactura recepcionFactura) {
		recepcionFacturaDao.agregar(recepcionFactura);		
	}

	@Override
	public void modificar(RecepcionFactura recepcionFactura) {
		recepcionFacturaDao.modificar(recepcionFactura);		
	}

	@Override
	public RecepcionFactura findById(Long id) {
		return recepcionFacturaDao.findById(id);
	}

	@Override
	public RecepcionFactura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception {
		return recepcionFacturaDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
	}

	@Override
	public Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma) {
		return recepcionFacturaDao.findByEstadoFirma(estadoFirma, reEstadoFirma);
	}

	@Override
	public Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave) {
		return recepcionFacturaDao.findByClave(cedulaEmisor, clave);
	}

}