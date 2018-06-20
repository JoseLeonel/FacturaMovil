package com.factura.FacturaElectronica.Bo.Impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.KardexBo;
import com.factura.FacturaElectronica.Dao.InventarioDao;
import com.factura.FacturaElectronica.Dao.KardexDao;
import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.modelo.Kardex;
import com.factura.FacturaElectronica.modelo.Usuario;

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
	 * @param inventario
	 * @param cantidad
	 * @param observacion
	 * @param consecutivo
	 * @param tipo
	 * @param motivo
	 * @param usuarioSesion
	 */
	public void entrada(Inventario inventario, BigDecimal cantidad, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {

		kardexDao.entrada(inventario, cantidad, observacion, consecutivo, tipo, motivo, usuarioSesion);
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
	public void salida(Inventario inventario, BigDecimal cantidad, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {
		kardexDao.salida(inventario, cantidad, observacion, consecutivo, tipo, motivo, usuarioSesion);

	}

}