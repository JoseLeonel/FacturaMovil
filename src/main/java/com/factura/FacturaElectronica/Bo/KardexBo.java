package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.modelo.Kardex;
import com.factura.FacturaElectronica.modelo.Usuario;

public interface KardexBo {

	void agregar(Kardex kardex);

	void entrada(Inventario inventario, Double cantidad, String observacion, String consecutivo, String tipo, String motivo,Usuario usuario);
	void salida(Inventario inventario, Double cantidad, String observacion, String consecutivo, String tipo, String motivo,Usuario usuario);

}