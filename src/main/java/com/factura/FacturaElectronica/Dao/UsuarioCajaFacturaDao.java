package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.UsuarioCajaFactura;

public interface UsuarioCajaFacturaDao {

	void agregar(UsuarioCajaFactura usuarioCajaFactura);

	void modificar(UsuarioCajaFactura usuarioCajaFactura);

	void eliminar(UsuarioCajaFactura usuarioCajaFactura);

	UsuarioCajaFactura buscar(Integer id);

}