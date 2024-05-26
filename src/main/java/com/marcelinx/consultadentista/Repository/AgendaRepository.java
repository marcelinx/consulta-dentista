package com.marcelinx.consultadentista.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelinx.consultadentista.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
