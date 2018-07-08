package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.KardexBo;
import com.emprendesoftcr.Dao.InventarioDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.modelo.Inventario;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Tranzabilida de los articulos en la sucursal KardexBoImpl.
 * @author jose.
 * @since 12 abr. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("kardexBo")
public class KardexBoImpl implements KardexBo {

	@Autowired
	KardexDao			kardexDao;

	@Autowired
	InventarioDao	inventarioDao;

	public void agregar(Kardex kardex) {
		kardexDao.agregar(kardex);
	}

	/**
	 * Ingresar Cantidad al inventario
	 * @see com.emprendesoftcr.Bo.KardexBo#entrada(com.emprendesoftcr.modelo.Inventario, java.lang.Double, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.emprendesoftcr.modelo.Usuario)
	 */
	public void entrada(Inventario inventario,Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {

		kardexDao.entrada(inventario, cantidadActual, cantidadNueva, observacion, consecutivo, tipo, motivo, usuarioSesion);
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
	public void salida(Inventario inventario, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {
		kardexDao.salida(inventario, cantidadActual, cantidadNueva, observacion, consecutivo, tipo, motivo, usuarioSesion);

	}

}