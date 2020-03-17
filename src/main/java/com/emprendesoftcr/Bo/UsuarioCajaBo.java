package com.emprendesoftcr.Bo;

import java.util.ArrayList;
import java.util.Collection;

import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.sqlNativo.UsuarioCajaCategoriaArticulo;
import com.emprendesoftcr.web.command.DenominacionCommand;

public interface UsuarioCajaBo {

	void agregar(UsuarioCaja usuarioCaja);

	void modificar(UsuarioCaja usuarioCaja);

	void eliminar(UsuarioCaja usuarioCaja);

	UsuarioCaja buscar(Long id);

	UsuarioCaja findByUsuarioAndEstado(Usuario usuario, String estado);

	UsuarioCaja cierreCaja(UsuarioCaja usuarioCaja, ArrayList<DenominacionCommand> listaCoteo, Usuario usuario) throws Exception;

	UsuarioCaja aperturaCaja(ArrayList<DenominacionCommand> listaCoteo, Usuario usuario,Caja caja) throws Exception;

	void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalServicio) throws Exception;

	void actualizarCaja(UsuarioCaja usuarioCaja) throws Exception;

	ArrayList<UsuarioCajaCategoriaArticulo> agrupaArticulosCategoria(Integer empresaId, Long usuarioCajaId);
	Collection<UsuarioCaja> usuarioCajaBy(Empresa empresa, String estado);
}