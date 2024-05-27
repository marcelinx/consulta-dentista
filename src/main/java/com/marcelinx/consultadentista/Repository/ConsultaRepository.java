package com.marcelinx.consultadentista.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelinx.consultadentista.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByAgendaId(Long agendaId);
}
