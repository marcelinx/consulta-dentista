package com.marcelinx.consultadentista.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcelinx.consultadentista.Repository.DentistaRepository;
import com.marcelinx.consultadentista.entities.Dentista;



@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

  @Autowired
  private DentistaRepository dentistaRepository;

  @Override
  public void run(String... args) throws Exception {

    Dentista u1 = new Dentista(null, "João", "joao@gmail.com", "Geral", "1111", "12345");
    Dentista u2 = new Dentista(null, "Beatryz", "beatryz@gmail.com", "Ortodontia", "12345", "1111");

    dentistaRepository.saveAll(Arrays.asList(u1, u2));
  }

}