package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Dao.DataTableDao;
import com.emprendesoftcr.Utils.DataTableDelimitador;



@Service("dataTableBo")
public class DataTableBoImpl implements DataTableBo {

	@Autowired
	private DataTableDao dataTableDao;

        @Override
	public Long contar(DataTableDelimitador delimitadores) {
		return dataTableDao.contar(delimitadores);
	}

        @Override
	public Collection<Object> listar(DataTableDelimitador delimitadores) {
		return dataTableDao.listar(delimitadores);
	}

}
