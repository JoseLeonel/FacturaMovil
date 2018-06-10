package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.UsuarioCaja;

public interface UsuarioCajaBo {

	void agregar(UsuarioCaja usuarioCaja);

	void modificar(UsuarioCaja usuarioCaja);

	void eliminar(UsuarioCaja usuarioCaja);

	UsuarioCaja buscar(Integer id);

}