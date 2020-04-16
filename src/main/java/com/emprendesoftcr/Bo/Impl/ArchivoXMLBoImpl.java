package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ArchivoXMLBo;
import com.emprendesoftcr.Dao.ArchivoXMLDao;
import com.emprendesoftcr.modelo.ArchivoXML;
import com.emprendesoftcr.modelo.Empresa;

@EnableTransactionManagement
@Service("archivoXMLBo")
public class ArchivoXMLBoImpl implements ArchivoXMLBo {

	@Autowired
	ArchivoXMLDao archivoXMLDao;

	@Transactional
	@Override
	public void agregar(ArchivoXML archivoXML) {
		archivoXMLDao.agregar(archivoXML);

	}

	@Transactional
	@Override
	public void modificar(ArchivoXML archivoXML) {
		archivoXMLDao.modificar(archivoXML);

	}

	@Transactional
	@Override
	public void eliminar(ArchivoXML archivoXML) {
		archivoXMLDao.eliminar(archivoXML);

	}

	@Override
	public ArchivoXML findById(Long id) {

		return archivoXMLDao.findById(id);
	}

	@Override
	public ArchivoXML findByIdFactura(Empresa empresa, Long idFactura) {

		return archivoXMLDao.findByIdFactura(empresa, idFactura);
	}

	@Override
	public ArchivoXML findByClave(Empresa empresa, String clave) {

		return archivoXMLDao.findByClave(empresa, clave);
	}

}
