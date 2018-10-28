package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.Utils.DataTableDelimitador;


public interface DataTableDao {

	 Long contar(DataTableDelimitador delimitadores);

	 Collection<Object> listar(DataTableDelimitador delimitadores);
}
