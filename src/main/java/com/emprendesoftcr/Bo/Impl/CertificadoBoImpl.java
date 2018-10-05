package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Dao.CertificadoDao;
import com.emprendesoftcr.fisco.P12Utils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.type.CertificateInfo;

@Transactional
@EnableTransactionManagement
@Service("certificadoBo")
public class CertificadoBoImpl implements CertificadoBo {

	@Autowired
	private CertificadoDao certificadoDao;

	@Override
	public void agregar(Empresa empresa, String password, String nombrep12) {
		CertificateInfo certificateInfo = null;

		try {
		//desarrollo
			//ClassPathResource cpr = new ClassPathResource("/home/jose/Escritorio/llaves/" + nombrep12);
			
			nombrep12 ="010691038708.p12";
			password = "0387";
			ClassPathResource cpr = new ClassPathResource("/home/jose/dev/llaves/produccion/" + nombrep12);
			String path = new ClassPathResource("/home/jose/dev/llaves/tmp/").getPath();
			certificateInfo = P12Utils.dataFromP12(path, cpr.getPath(), password);

			Certificado certificado = new Certificado();
			certificado.setEmpresa(empresa);
			certificado.setCerthash(certificateInfo.certHash());
			certificado.setCertificate(certificateInfo.certificate());
			certificado.setPrivateKey(certificateInfo.privateKey());
			certificado.setX509issuerName(certificateInfo.x509IssuerName());
			certificado.setX509serialNumber(certificateInfo.x509SerialNumber());
			certificado.setCreated_at(new Date());
			certificado.setUpdated_at(new Date());
			certificadoDao.agregar(certificado);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Certificado findById(Integer id) {

		return certificadoDao.findById(id);
	}

	@Override
	public Certificado findByEmpresa(Empresa empresa) {

		return certificadoDao.findByEmpresa(empresa);
	}

	@Override
	public Collection<Certificado> findByAll() {

		return certificadoDao.findByAll();
	}

}
