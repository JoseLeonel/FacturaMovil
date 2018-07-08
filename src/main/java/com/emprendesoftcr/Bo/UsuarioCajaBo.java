package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;

public interface UsuarioCajaBo {

	void agregar(UsuarioCaja usuarioCaja);

	void modificar(UsuarioCaja usuarioCaja);

	void eliminar(UsuarioCaja usuarioCaja);

	UsuarioCaja buscar(Integer id);
	UsuarioCaja findByUsuarioAndEstado(Usuario usuario,String estado);
	
	void cierreCaja(UsuarioCaja usuarioCaja) throws Exception;
	
	void actualizarCaja(UsuarioCaja usuarioCaja,Double totalEfectivo,Double totalTarjeta,Double totalBanco,Double totalCredito,Double totalAbono)throws Exception;

}