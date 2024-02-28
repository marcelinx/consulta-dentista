package com.marcelinx.consultadentista.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelinx.consultadentista.entities.Dentista;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/dentistas")
public class DentistaResource {

  @GetMapping
  public ResponseEntity<Dentista> findAll() {
    Dentista d1 = new Dentista(1L, "João Marcelino", "joao@gmail.com", "Ortodontia", "1111", "12345");

    return ResponseEntity.ok().body(d1);
  }
}
