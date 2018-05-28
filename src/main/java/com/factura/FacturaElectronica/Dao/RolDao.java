package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Rol;

public interface RolDao {

	Rol buscarPorUserName(String username);

}