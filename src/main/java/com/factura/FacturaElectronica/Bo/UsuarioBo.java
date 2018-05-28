package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.Utils.DataTableFilter;
import com.factura.FacturaElectronica.modelo.Usuario;

public interface UsuarioBo {

	void agregar(Usuario usuario);

	void modificar(Usuario usuario);

	void eliminar(Usuario usuario);

	Usuario buscar(String usuarioQAM, Integer estado);

	Usuario buscar(String nombreUsuario);

	Usuario buscar(Integer id);

	Usuario buscarPorNombreYPrimerApellidoYSegundoApellido(String nombre, String primerApellido, String segundoApellido);
	
	DataTableFilter filtroPorEmpresa(String nombreUsuario);

}