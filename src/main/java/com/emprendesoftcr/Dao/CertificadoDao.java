package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Empresa;

public interface CertificadoDao {

	void agregar(Certificado certificado);

	Certificado findById(Integer id);	
	
	Certificado findByEmpresa(Empresa empresa);
	
	Collection<Certificado> findByAll();

}