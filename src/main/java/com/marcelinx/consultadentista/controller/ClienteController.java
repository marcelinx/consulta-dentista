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

import com.marcelinx.consultadentista.Repository.ClienteRepository;
import com.marcelinx.consultadentista.model.Cliente;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consultorio/clientes")
@AllArgsConstructor
public class ClienteController {

  @Autowired
  private ClienteRepository clienteRepository;

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
          cliente.setTelefone(clienteDetalhes.getTelefone());
          cliente.setDataNascimento(clienteDetalhes.getDataNascimento());
          cliente.setSexo(clienteDetalhes.getSexo());
          cliente.setEndereco(clienteDetalhes.getEndereco());
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
