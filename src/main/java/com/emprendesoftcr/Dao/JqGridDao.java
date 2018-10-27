package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.Utils.JqGridDelimitador;

/**
 * 
 * JqGridDao.
 * @author Leonel Hernandez Chaverri.
 * @since 13 abr. 2018
 */
public interface JqGridDao {

	Long contar(JqGridDelimitador delimitadores);

	Collection<Object> listar(JqGridDelimitador delimitadores);
}
