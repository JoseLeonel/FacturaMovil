package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.Utils.JqGridFilter;
import com.factura.FacturaElectronica.modelo.Usuario;

public interface UsuarioBo {

	void agregar(Usuario usuario);

	void modificar(Usuario usuario);

	void eliminar(Usuario usuario);

	Usuario buscar(String usuarioQAM, Integer estado);

	Usuario buscar(String nombreUsuario);

	Usuario buscar(Integer id);

	Usuario buscarPorNombreYPrimerApellidoYSegundoApellido(String nombre, String primerApellido, String segundoApellido);
	
	JqGridFilter filtroPorEmpresa(String nombreUsuario);

}