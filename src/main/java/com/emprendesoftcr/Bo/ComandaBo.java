package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.ComandaMesa;

public interface ComandaBo {

	void agregar(ComandaMesa comandaMesa);

	void modificar(ComandaMesa comandaMesa);

	void eliminar(ComandaMesa comandaMesa);

	void actualizarComandaMesaFacturas(Long idMesa);
	
	ComandaMesa buscar(Long id);

	Collection<ComandaMesa> buscarPorFacturaMesa(Long idFactura, Long idMesa);

}