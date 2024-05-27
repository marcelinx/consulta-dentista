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
@RequestMapping("/consultorio/consultas")
@AllArgsConstructor
public class ConsultaController {


    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping
    public @ResponseBody List<Consulta> list() {
        return consultaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> findById(@PathVariable Long id) {
        return consultaRepository.findById(id)
            .map(consulta -> ResponseEntity.ok().body(consulta))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Consulta create(@RequestBody Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody Consulta consultaDetalhes) {
        return consultaRepository.findById(id)
            .map(consulta -> {
                consulta.setHora(consultaDetalhes.getHora());
                consulta.setDentista(consultaDetalhes.getDentista());
                consulta.setCliente(consultaDetalhes.getCliente());
    
                // Atualizar a consulta afeta a agenda associada
                Agenda agenda = consulta.getAgenda();
                if (agenda != null) {
                    agenda.setDentista(consultaDetalhes.getDentista());
                    agendaRepository.save(agenda);
                }
    
                Consulta updated = consultaRepository.save(consulta);
                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return consultaRepository.findById(id)
            .map(consulta -> {
                consultaRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
