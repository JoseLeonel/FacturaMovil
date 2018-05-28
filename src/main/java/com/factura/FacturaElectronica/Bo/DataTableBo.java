package com.factura.FacturaElectronica.Bo;

import java.util.Collection;

import com.factura.FacturaElectronica.Utils.DataTableDelimitador;

public interface DataTableBo {

	Long contar(DataTableDelimitador delimitadores);

	Collection<Object> listar(DataTableDelimitador delimitadores);

}
