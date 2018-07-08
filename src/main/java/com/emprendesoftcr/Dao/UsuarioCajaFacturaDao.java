package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.UsuarioCajaFactura;

public interface UsuarioCajaFacturaDao {

	void agregar(UsuarioCajaFactura usuarioCajaFactura);

	void modificar(UsuarioCajaFactura usuarioCajaFactura);

	void eliminar(UsuarioCajaFactura usuarioCajaFactura);

	UsuarioCajaFactura buscar(Integer id);

}