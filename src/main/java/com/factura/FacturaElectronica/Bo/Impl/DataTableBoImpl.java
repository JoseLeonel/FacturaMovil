package com.factura.FacturaElectronica.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Dao.DataTableDao;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;



@Service("dataTableBo")
public class DataTableBoImpl implements DataTableBo {

	@Autowired
	DataTableDao dataTableDao;

        @Override
	public Long contar(DataTableDelimitador delimitadores) {
		return dataTableDao.contar(delimitadores);
	}

        @Override
	public Collection<Object> listar(DataTableDelimitador delimitadores) {
		return dataTableDao.listar(delimitadores);
	}

}
