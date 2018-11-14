package com.emprendesoftcr.Dao;

import java.util.ArrayList;

import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.sqlNativo.UsuarioCajaCategoriaArticulo;

public interface UsuarioCajaDao {

	void agregar(UsuarioCaja usuarioCaja);

	void modificar(UsuarioCaja usuarioCaja);

	void eliminar(UsuarioCaja usuarioCaja);

	UsuarioCaja buscar(Long id);
	
	UsuarioCaja findByUsuarioAndEstado(Usuario usuario,String estado);
	
	void actualizarCaja(UsuarioCaja usuarioCaja ,Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalServicio)throws Exception;

	void actualizarCaja(UsuarioCaja usuarioCaja)throws Exception;
	
	ArrayList<UsuarioCajaCategoriaArticulo> agrupaArticulosCategoria(Integer empresaId, Long usuarioCajaId);

}