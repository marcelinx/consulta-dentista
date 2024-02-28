package com.marcelinx.consultadentista.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelinx.consultadentista.entities.Dentista;

public interface DentistaRepository extends JpaRepository <Dentista, Long>{
  
}
