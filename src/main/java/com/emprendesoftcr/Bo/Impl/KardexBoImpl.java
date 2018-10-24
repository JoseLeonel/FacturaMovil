package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.KardexBo;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Tranzabilida de los articulos en la sucursal KardexBoImpl.
 * @author jose.
 * @since 12 abr. 2018
 */
@EnableTransactionManagement
@Service("kardexBo")
public class KardexBoImpl implements KardexBo {

	private Logger		log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KardexDao	kardexDao;

	@Override
	@Transactional
	public void agregar(Kardex kardex) {
		kardexDao.agregar(kardex);
	}

	/**
	 * Ingresar Cantidad al inventario
	 * @throws Exception
	 * @see com.emprendesoftcr.Bo.KardexBo#entrada(com.emprendesoftcr.modelo.Inventario, java.lang.Double, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.emprendesoftcr.modelo.Usuario)
	 */
	@Override
	@Transactional
	public void entrada(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) throws Exception {
		try {
			kardexDao.entrada(articulo, cantidadActual, cantidadNueva, observacion, consecutivo, tipo, motivo, usuarioSesion);
		} catch (Exception e) {
			log.info("** Error  entrada kardex: " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

	}

	/**
	 * Salida del inventario
	 * @param inventario
	 * @param cantidad
	 * @param observacion
	 * @param consecutivo
	 * @param tipo
	 * @param motivo
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void salida(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) throws Exception {
		try {
			kardexDao.salida(articulo, cantidadActual, cantidadNueva, observacion, consecutivo, tipo, motivo, usuarioSesion);
		} catch (Exception e) {
			log.info("** Error  salida kardex: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

}