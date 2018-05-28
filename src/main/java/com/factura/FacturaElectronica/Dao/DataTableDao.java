package com.factura.FacturaElectronica.Dao;

import java.util.Collection;

import com.factura.FacturaElectronica.Utils.DataTableDelimitador;


public interface DataTableDao {

	 Long contar(DataTableDelimitador delimitadores);

	 Collection<Object> listar(DataTableDelimitador delimitadores);
}
