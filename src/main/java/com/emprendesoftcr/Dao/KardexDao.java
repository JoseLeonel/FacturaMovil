package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

public interface KardexDao {

	void agregar(Kardex Kardex);

	void entrada(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) throws Exception;

	void salida(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) throws Exception;

}