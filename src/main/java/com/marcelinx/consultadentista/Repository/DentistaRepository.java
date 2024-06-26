package com.marcelinx.consultadentista.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelinx.consultadentista.model.Dentista;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

}
