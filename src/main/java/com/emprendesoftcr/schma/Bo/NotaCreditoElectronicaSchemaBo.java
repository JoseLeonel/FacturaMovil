package com.emprendesoftcr.schma.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;

public interface NotaCreditoElectronicaSchemaBo {
	public void aplicarFacturaXMLToModelo(String ruta, Empresa empresa, Usuario usuarioSesion, String condicionImpuesto, Integer tipoGasto, String codigoActividad, String mensaje, String detalleMensaje) throws Exception;
}
