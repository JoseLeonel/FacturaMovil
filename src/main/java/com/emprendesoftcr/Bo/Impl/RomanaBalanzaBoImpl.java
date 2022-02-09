package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.RomanaBalanzaBo;
import com.emprendesoftcr.Dao.RomanaBalanzaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RomadaBalanza;

@EnableTransactionManagement
@Service("romanaBalanzaBo")
public class RomanaBalanzaBoImpl implements RomanaBalanzaBo {

	@Autowired
	RomanaBalanzaDao romanaBalanzaDao;

	@Override
	@Transactional
	public void agregar(RomadaBalanza romadaBalanza) {
		romanaBalanzaDao.agregar(romadaBalanza);
	}

	@Override
	@Transactional
	public void modificar(RomadaBalanza romadaBalanza) {
		romanaBalanzaDao.modificar(romadaBalanza);

	}

	@Override
	@Transactional
	public void eliminar(RomadaBalanza romadaBalanza) {
		romanaBalanzaDao.eliminar(romadaBalanza);

	}

	@Override
	@Transactional
	public RomadaBalanza buscar(Long id) {
		
		return romanaBalanzaDao.buscar(id);
	}

	@Override
	@Transactional
	public RomadaBalanza buscarPorEmpresa(Empresa empresa) {
		
		return romanaBalanzaDao.buscarPorEmpresa(empresa);
	}

}
