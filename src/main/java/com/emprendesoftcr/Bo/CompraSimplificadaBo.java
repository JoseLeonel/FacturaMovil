package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CompraSimplificadaCommand;

public interface CompraSimplificadaBo {


	void agregar(CompraSimplificada compraSimplificada);

	void modificar(CompraSimplificada compraSimplificada);

	void eliminar(CompraSimplificada compraSimplificada);
	
	CompraSimplificada crearCompraSimplificada(CompraSimplificadaCommand facturaCommand,Usuario usuario,TipoCambio tipoCambio) throws Exception;

	CompraSimplificada findById(Long id);

	CompraSimplificada findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleComprasPorSP(CompraSimplificada compraSimplificada) throws Exception;
	


}