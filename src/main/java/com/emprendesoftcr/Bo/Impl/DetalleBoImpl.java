package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;


@EnableTransactionManagement
@Service("detalleBo")
public class DetalleBoImpl implements DetalleBo {

	@Autowired
	private DetalleDao	detalleDao;

	private Logger			log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(Detalle detalle) {
		detalleDao.agregar(detalle);

	}

	@Transactional
	@Override
	public void modificar(Detalle detalle) {
		detalleDao.modificar(detalle);
	}

	@Transactional
	@Override
	public void eliminar(Detalle detalle) {
		detalleDao.eliminar(detalle);

	}

	@Transactional
	@Override
	public Integer eliminarDetalleFactura(Factura factura) throws Exception {
		try {
			return detalleDao.eliminarDetalleFactura(factura);
		} catch (Exception e) {
			log.info("** Error  eliminarDetalleFactura: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}

	}
	
	@Override
	public Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, String codigo, String tipoDocumento, Cliente cliente, Empresa empresa, Usuario usuario)
	{
		return detalleDao.facturasRangoEstado(estado, fechaInicio, fechaFin, codigo, tipoDocumento, cliente, empresa, usuario);
	}

}
