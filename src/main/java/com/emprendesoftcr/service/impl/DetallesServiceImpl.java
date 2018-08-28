package com.emprendesoftcr.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.repository.DetallesRepository;
import com.emprendesoftcr.service.DetallesService;

@Service("detallesService")
@Transactional
@EnableTransactionManagement
public class DetallesServiceImpl implements DetallesService {

	@Autowired
	private DetallesRepository detallesRepository;
	
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void sp_EliminarDetalles(Integer idFactura_param) {
		try {
			log.info("** Inicio de la ejecucion del procedimiento almacendos eliminar detalles de la factura : " + " fecha " + new Date());
			  detallesRepository.sp_EliminarDetalles(idFactura_param);
			log.info("** Fin de la ejecucion del procedimiento almacendos eliminar detalles de la factura : " + " fecha " + new Date());

		} catch (Exception e) {
			log.error("** Error ejecutar el procedimineto almacenados de eliminar detalles de la factura : " + e.getMessage() + " fecha " + new Date());
		}
	}

}
