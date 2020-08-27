package com.emprendesoftcr.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.modelo.CuentaCobrar;

public interface CuentaCobrarRepository extends CrudRepository<CuentaCobrar, Long> {

	@Query("SELECT c FROM CuentaCobrar c WHERE c.notificacion = ?1 AND c.cliente.id = ?2")
	public Collection<CuentaCobrar> getNotificacionAndCliente(Integer notificacion, Long idCliente);
	
	@Query("SELECT c FROM CuentaCobrar c WHERE c.estado = ?1 AND c.cliente.id = ?2")
	public Collection<CuentaCobrar> getEstadoAndCliente( String estado, Long idCliente);
	
	@Transactional
	@Modifying
	@Query("UPDATE CuentaCobrar c SET c.notificacion=?1 WHERE c.id=?2")
	public void updateEstado(Integer notificacion, Long id);
	
	
	
}
