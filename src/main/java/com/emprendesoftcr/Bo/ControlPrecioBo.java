package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.ControlPrecioArticulo;
import com.emprendesoftcr.modelo.Usuario;

public interface  ControlPrecioBo {
	void agregar(ControlPrecioArticulo controlPrecioArticulo);

	void modificar(ControlPrecioArticulo controlPrecioArticulo);

	void eliminar(ControlPrecioArticulo controlPrecioArticulo);

	ControlPrecioArticulo buscar(Long id);
	void agregarControlPrecio(Articulo articuloCambio, Articulo articuloActual, String nota,  String rutaArchivoCompra, String consecutivoCompra,String clave, Usuario usuarioResponsable);

}
