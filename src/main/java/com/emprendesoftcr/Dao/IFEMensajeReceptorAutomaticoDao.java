package com.emprendesoftcr.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

public interface IFEMensajeReceptorAutomaticoDao extends CrudRepository<FEMensajeReceptorAutomatico, Long> {
	
	@Query("SELECT c FROM FEMensajeReceptorAutomatico c WHERE c.estado = ?1 AND c.receptorIdentificacion = ?2")
	public List<FEMensajeReceptorAutomatico> getAll(String estado, String identifiacionCliente);
	
	@Modifying
	@Query("UPDATE FEMensajeReceptorAutomatico c SET c.estado=?1 WHERE c.id=?2")
	public void updateEstado(String estado, Long id);
	
	
}
