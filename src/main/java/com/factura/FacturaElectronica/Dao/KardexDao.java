package com.factura.FacturaElectronica.Dao;

import java.math.BigDecimal;

import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.modelo.Kardex;
import com.factura.FacturaElectronica.modelo.Usuario;

public interface KardexDao {

	void agregar(Kardex Kardex);
	
	void entrada(Inventario inventario, BigDecimal cantidad, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion);
	void salida(Inventario inventario, BigDecimal cantidad, String observacion, String consecutivo, String tipo, String motivo,Usuario usuario);


	

	
}