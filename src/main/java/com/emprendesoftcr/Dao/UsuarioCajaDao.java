package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;

public interface UsuarioCajaDao {

	void agregar(UsuarioCaja usuarioCaja);

	void modificar(UsuarioCaja usuarioCaja);

	void eliminar(UsuarioCaja usuarioCaja);

	UsuarioCaja buscar(Integer id);
	
	UsuarioCaja findByUsuarioAndEstado(Usuario usuario,String estado);
	
	void actualizarCaja(UsuarioCaja usuarioCaja ,Double totalEfectivo,Double totalTarjeta,Double totalBanco,Double totalCredito,Double totalAbono)throws Exception;

}