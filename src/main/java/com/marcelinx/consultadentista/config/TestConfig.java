package com.marcelinx.consultadentista.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.marcelinx.consultadentista.model.Cliente;
import com.marcelinx.consultadentista.model.Dentista;
import com.marcelinx.consultadentista.repository.ClienteRepository;
import com.marcelinx.consultadentista.repository.DentistaRepository;

@Configuration
@Profile("test")
public class TestConfig {

  @Bean
  public CommandLineRunner initDataBase(DentistaRepository dentistaRepository, ClienteRepository clienteRepository) {
    return args -> {
      dentistaRepository.deleteAll();
      clienteRepository.deleteAll();

      Dentista d1 = new Dentista();
      d1.setName("João");
      d1.setCategory("Geral");
      d1 = dentistaRepository.save(d1);

      Cliente c1 = new Cliente();
      c1.setNome("Maria");
      c1.setEmail("maria@example.com");
      c1.setDentista(d1);

      clienteRepository.save(c1);
    };
  }
}
