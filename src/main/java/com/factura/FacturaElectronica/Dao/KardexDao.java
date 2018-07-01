package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.modelo.Kardex;
import com.factura.FacturaElectronica.modelo.Usuario;

public interface KardexDao {

	void agregar(Kardex Kardex);
	
	void entrada(Inventario inventario,Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion);
	void salida(Inventario inventario, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion);


	

	
}