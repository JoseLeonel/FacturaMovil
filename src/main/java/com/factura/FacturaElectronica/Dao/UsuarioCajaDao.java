package com.factura.FacturaElectronica.Dao;

import java.math.BigDecimal;

import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

public interface UsuarioCajaDao {

	void agregar(UsuarioCaja usuarioCaja);

	void modificar(UsuarioCaja usuarioCaja);

	void eliminar(UsuarioCaja usuarioCaja);

	UsuarioCaja buscar(Integer id);
	
	UsuarioCaja findByUsuarioAndEstado(Usuario usuario,String estado);
	
	void actualizarCaja(UsuarioCaja usuarioCaja ,BigDecimal totalEfectivo,BigDecimal totalTarjeta,BigDecimal totalBanco,BigDecimal totalCredito,BigDecimal totalAbono);

}