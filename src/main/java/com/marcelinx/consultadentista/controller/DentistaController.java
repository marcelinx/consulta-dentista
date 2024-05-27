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

import com.marcelinx.consultadentista.Repository.DentistaRepository;
import com.marcelinx.consultadentista.model.Dentista;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consultorio/colaboradores")
@AllArgsConstructor
public class DentistaController {

    @Autowired
    private DentistaRepository dentistaRepository;

    @GetMapping
    public @ResponseBody List<Dentista> list() {
        return dentistaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> findById(@PathVariable Long id) {
        return dentistaRepository.findById(id)
                .map(dentista -> ResponseEntity.ok().body(dentista))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Dentista create(@RequestBody Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentista> update(@PathVariable Long id, @RequestBody Dentista dentista) {
        return dentistaRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(dentista.getName());
                    recordFound.setCategory(dentista.getCategory());
                    recordFound.setCro(dentista.getCro());
                    recordFound.setEndereco(dentista.getEndereco());
                    recordFound.setTelefone(dentista.getTelefone());

                    Dentista updated = dentistaRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return dentistaRepository.findById(id)
                .map(dentista -> {
                    dentistaRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
