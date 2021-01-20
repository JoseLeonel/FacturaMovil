package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.CorreoAutomaticoBo;
import com.emprendesoftcr.Dao.CorreoAutomaticoDao;
import com.emprendesoftcr.modelo.CorreoAutomatico;

@EnableTransactionManagement
@Service("correoAutomaticoBo")
public class CorreoAutomaticoBoImpl implements CorreoAutomaticoBo {

	@Autowired
	private CorreoAutomaticoDao correoAutomaticoDao;

	@Override
	public Collection<CorreoAutomatico> allEmails() {

		return correoAutomaticoDao.allEmails();
	}

}
