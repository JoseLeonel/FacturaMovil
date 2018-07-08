package com.emprendesoftcr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.modelo.Rol;

@Repository
public interface RolRepository  extends JpaRepository<Rol, Integer>{

}
