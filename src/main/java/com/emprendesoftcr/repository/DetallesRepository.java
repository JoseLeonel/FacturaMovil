package com.emprendesoftcr.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.modelo.Detalle;


@Repository
@Transactional
public interface  DetallesRepository extends CrudRepository<Detalle,Integer > {

	
	@Procedure(procedureName="sp_eliminarDetallesFactura")
	void sp_EliminarDetalles(@Param("idFactura_param") Integer idFactura_param); 
	
	
	
	

}
