package com.marcelinx.consultadentista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelinx.consultadentista.Repository.AgendaRepository;
import com.marcelinx.consultadentista.Repository.ConsultaRepository;
import com.marcelinx.consultadentista.model.Agenda;
import com.marcelinx.consultadentista.model.Consulta;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consultorio/agendas")
@AllArgsConstructor
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @GetMapping
    public @ResponseBody List<Agenda> list() {
        return agendaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> findById(@PathVariable Long id) {
        return agendaRepository.findById(id)
            .map(agenda -> ResponseEntity.ok().body(agenda))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Agenda create(@RequestBody Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> update(@PathVariable Long id, @RequestBody Agenda agendaDetalhes) {
        return agendaRepository.findById(id)
            .map(agenda -> {
                agenda.setDate(agendaDetalhes.getDate());
                agenda.setCliente(agendaDetalhes.getCliente());
                agenda.setDentista(agendaDetalhes.getDentista());
    
                // Atualizar a agenda afeta as consultas associadas
                List<Consulta> consultas = consultaRepository.findByAgendaId(id);
                for (Consulta consulta : consultas) {
                    consulta.setDentista(agendaDetalhes.getDentista());
                    consultaRepository.save(consulta);
                }
    
                Agenda updated = agendaRepository.save(agenda);
                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return agendaRepository.findById(id)
            .map(cliente -> {
                agendaRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
