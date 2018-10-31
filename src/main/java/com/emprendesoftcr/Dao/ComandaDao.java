package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.ComandaMesa;

public interface ComandaDao {

	void agregar(ComandaMesa comandaMesa);

	void modificar(ComandaMesa comandaMesa);

	void eliminar(ComandaMesa comandaMesa);

	ComandaMesa buscar(Long id);

	Collection<ComandaMesa> buscarPorFacturaMesa(Long idFactura, Long idMesa);

}