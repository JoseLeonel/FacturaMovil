package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.Utils.JqGridDelimitador;



public interface JqGridBo {

	public Long contar(JqGridDelimitador delimitadores);

	public Collection<Object> listar(JqGridDelimitador delimitadores);

}
