package com.emprendesoftcr.Bo;

import java.util.ArrayList;

import com.emprendesoftcr.modelo.Cocina;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.ComandaCocinaCommand;

public interface CocinaBo {
	
	void agregar(Cocina cocina);

	void modificar(Cocina cocina);

	void eliminar(Cocina cocina);

	Cocina buscar(Long id);
	
	Cocina buscarCocinaActiva(UsuarioCaja usuarioCaja);
	
	void agregarNuevaCocinaDia(UsuarioCaja usuarioCaja);
	
	void agregarNuevaComandaDia(Cocina cocina, ArrayList<ComandaCocinaCommand> comandaCocinaCommands ) throws Exception;

}
