package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Rol;

public interface RolBo {

	Rol buscarPorUserName(String username);

}