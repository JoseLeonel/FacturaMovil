package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.Utils.DataTableDelimitador;

public interface DataTableBo {

	Long contar(DataTableDelimitador delimitadores);

	Collection<Object> listar(DataTableDelimitador delimitadores);

}
