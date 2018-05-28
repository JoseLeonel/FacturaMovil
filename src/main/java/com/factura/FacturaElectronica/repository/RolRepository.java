package com.factura.FacturaElectronica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.modelo.Rol;

@Repository
public interface RolRepository  extends JpaRepository<Rol, Integer>{

}
