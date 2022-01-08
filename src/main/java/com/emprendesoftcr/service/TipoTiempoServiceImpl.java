package com.emprendesoftcr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.modelo.TipoTiempo;
import com.emprendesoftcr.repository.TipoTiempoRepository;

@Service("tipoTiempoService")
public class TipoTiempoServiceImpl implements TipoTiempoService {

	@Autowired
	private TipoTiempoRepository tipoTiempoRepository;

	@Override
	public List<TipoTiempo> listarTodo() {
		return (List<TipoTiempo>) tipoTiempoRepository.findAll();
	}

	@Override
	public void guardar(TipoTiempo tipoTiempo) {
		tipoTiempoRepository.save(tipoTiempo);

	}

	@Override
	public TipoTiempo findbyId(Long id) {
		
		return tipoTiempoRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		tipoTiempoRepository.deleteById(id);;

	}

}
