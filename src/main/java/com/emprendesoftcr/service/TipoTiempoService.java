package com.emprendesoftcr.service;

import java.util.List;

import com.emprendesoftcr.modelo.TipoTiempo;

public interface TipoTiempoService {
	
	public List<TipoTiempo> listarTodo();
	public void guardar(TipoTiempo tipoTiempo);
	public TipoTiempo findbyId(Long id);
	public void eliminar(Long id);

}
