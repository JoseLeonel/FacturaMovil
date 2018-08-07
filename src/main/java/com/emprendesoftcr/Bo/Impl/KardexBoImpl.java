package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.KardexBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Tranzabilida de los articulos en la sucursal KardexBoImpl.
 * @author jose.
 * @since 12 abr. 2018
 */
@Lazy
@Transactional
@EnableTransactionManagement
@Service("kardexBo")
public class KardexBoImpl implements KardexBo {

	@Lazy
	@Autowired
	KardexDao		kardexDao;

	@Lazy
	@Autowired
	ArticuloDao	articuloDao;

	public void agregar(Kardex kardex) {
		kardexDao.agregar(kardex);
	}

	/**
	 * Ingresar Cantidad al inventario
	 * @see com.emprendesoftcr.Bo.KardexBo#entrada(com.emprendesoftcr.modelo.Inventario, java.lang.Double, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.emprendesoftcr.modelo.Usuario)
	 */
	public void entrada(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {

		kardexDao.entrada(articulo, cantidadActual, cantidadNueva, observacion, consecutivo, tipo, motivo, usuarioSesion);
	}

	/**
	 * Salida del inventario
	 * @param inventario
	 * @param cantidad
	 * @param observacion
	 * @param consecutivo
	 * @param tipo
	 * @param motivo
	 */
	public void salida(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {
		kardexDao.salida(articulo, cantidadActual, cantidadNueva, observacion, consecutivo, tipo, motivo, usuarioSesion);

	}

}