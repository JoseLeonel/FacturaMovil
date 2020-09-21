package com.emprendesoftcr.Bo.Impl;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.Bo.JqGridBo;
import com.emprendesoftcr.Dao.JqGridDao;
import com.emprendesoftcr.utils.JqGridDelimitador;

@Service("jqGridBo")
public class JqGridBoImpl implements JqGridBo {

	@Autowired
	JqGridDao jqGridDao;

	public Long contar(JqGridDelimitador delimitadores) {
		return jqGridDao.contar(delimitadores);
	}

	public Collection<Object> listar(JqGridDelimitador delimitadores) {
		return jqGridDao.listar(delimitadores);
	}

}
