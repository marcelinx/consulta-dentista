package com.marcelinx.consultadentista.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelinx.consultadentista.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
