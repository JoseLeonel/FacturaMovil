package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Empresa;

public interface CertificadoBo {
	void agregar(Empresa empresa,String password,String nombrep12);

	Certificado findById(Integer id);	
	
	Certificado findByEmpresa(Empresa empresa);
	
	Collection<Certificado> findByAll();
	
	
}
