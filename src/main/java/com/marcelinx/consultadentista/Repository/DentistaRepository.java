package com.marcelinx.consultadentista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelinx.consultadentista.model.Dentista;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

}
