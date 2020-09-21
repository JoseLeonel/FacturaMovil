package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.utils.DataTableDelimitador;


public interface DataTableDao {

	 Long contar(DataTableDelimitador delimitadores);

	 Collection<Object> listar(DataTableDelimitador delimitadores);
}
