package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.FacturaCommand;

public interface FacturaBo {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Integer id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception;
	
	Factura crearFactura(FacturaCommand facturaCommand,Usuario usuario,UsuarioCaja usuarioCaja,TipoCambio tipoCambio) throws Exception;

	Double  getTotalEfectivo(FacturaCommand facturaCommand) throws Exception;

}