package com.marcelinx.consultadentista.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marcelinx.consultadentista.model.Dentista;
import com.marcelinx.consultadentista.repository.DentistaRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/consultorio/colaboradores")
@AllArgsConstructor
public class DentistaController {

  private final DentistaRepository dentistaRepository;

  @GetMapping
  public @ResponseBody List<Dentista> list() {
    return dentistaRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Dentista> findById(@PathVariable Long id) {
    return dentistaRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Dentista create(@RequestBody Dentista Dentista) {
    return dentistaRepository.save(Dentista);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Dentista> update(@PathVariable Long id, @RequestBody Dentista Dentista) {
    return dentistaRepository.findById(id)
        .map(recordFound -> {
          recordFound.setName(Dentista.getName());
          recordFound.setCategory(Dentista.getCategory());

          Dentista updated = dentistaRepository.save(recordFound);

          return ResponseEntity.ok().body(updated);

        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return dentistaRepository.findById(id)
        .map(record -> {
          dentistaRepository.deleteById(id);
          return ResponseEntity.noContent().<Void>build(); 
        })
        .orElse(ResponseEntity.notFound().build());
  }
}