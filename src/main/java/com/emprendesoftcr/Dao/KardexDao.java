package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Inventario;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

public interface KardexDao {

	void agregar(Kardex Kardex);
	
	void entrada(Inventario inventario,Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion);
	void salida(Inventario inventario, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion);


	

	
}