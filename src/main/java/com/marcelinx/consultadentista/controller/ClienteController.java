package com.marcelinx.consultadentista.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marcelinx.consultadentista.model.Cliente;
import com.marcelinx.consultadentista.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consultorio/clientes")
@AllArgsConstructor
public class ClienteController {

  private final ClienteRepository clienteRepository;

  @GetMapping
  public @ResponseBody List<Cliente> list() {
    return clienteRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    return clienteRepository.findById(id)
        .map(cliente -> ResponseEntity.ok().body(cliente))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Cliente create(@RequestBody Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente clienteDetalhes) {
    return clienteRepository.findById(id)
        .map(cliente -> {
          cliente.setNome(clienteDetalhes.getNome());
          cliente.setEmail(clienteDetalhes.getEmail());
          Cliente updated = clienteRepository.save(cliente);
          return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    return clienteRepository.findById(id)
        .map(cliente -> {
          clienteRepository.deleteById(id);
          return ResponseEntity.noContent().<Void>build(); 
        })
        .orElse(ResponseEntity.notFound().build());
  }
}
