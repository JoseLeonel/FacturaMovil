package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.utils.JqGridDelimitador;



public interface JqGridBo {

	public Long contar(JqGridDelimitador delimitadores);

	public Collection<Object> listar(JqGridDelimitador delimitadores);

}
