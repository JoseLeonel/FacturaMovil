package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

public interface KardexBo {

	void agregar(Kardex kardex);

	void entrada(Articulo articulo,Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion);
	void salida(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion);

}