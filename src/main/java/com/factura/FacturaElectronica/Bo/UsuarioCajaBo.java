package com.factura.FacturaElectronica.Bo;

import java.math.BigDecimal;

import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

public interface UsuarioCajaBo {

	void agregar(UsuarioCaja usuarioCaja);

	void modificar(UsuarioCaja usuarioCaja);

	void eliminar(UsuarioCaja usuarioCaja);

	UsuarioCaja buscar(Integer id);
	UsuarioCaja findByUsuarioAndEstado(Usuario usuario,String estado);
	
	void cierreCaja(UsuarioCaja usuarioCaja);
	
	void actualizarCaja(UsuarioCaja usuarioCaja,BigDecimal totalEfectivo,BigDecimal totalTarjeta,BigDecimal totalBanco,BigDecimal totalCredito,BigDecimal totalAbono);

}