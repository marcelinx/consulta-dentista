package com.marcelinx.consultadentista.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcelinx.consultadentista.Repository.AgendaRepository;
import com.marcelinx.consultadentista.Repository.ClienteRepository;
import com.marcelinx.consultadentista.Repository.ConsultaRepository;
import com.marcelinx.consultadentista.Repository.DentistaRepository;
import com.marcelinx.consultadentista.model.Agenda;
import com.marcelinx.consultadentista.model.Cliente;
import com.marcelinx.consultadentista.model.Consulta;
import com.marcelinx.consultadentista.model.Dentista;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private DentistaRepository dentistaRepository;
	
	@Autowired
	private	ConsultaRepository consultaRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		var c1 = new Cliente(null, "Marcelino", "marcelino@gmail.com");
		var d1 = new Dentista(null, "Otavio", "Geral");
		var d2 = new Dentista(null, "Otavio2", "Geral");
		var cc1 = new Consulta(null,LocalTime.now(), c1, d1);
		var a1 = new Agenda(null, LocalDate.now(), cc1.getCliente(), cc1.getDentista());
		
		cc1.setAgenda(a1);
	
		
		clienteRepository.saveAll(Arrays.asList(c1));
		dentistaRepository.saveAll(Arrays.asList(d1,d2));
		agendaRepository.saveAll(Arrays.asList(a1));
		consultaRepository.saveAll(Arrays.asList(cc1));
		
	}
		
  //@Bean
  
}
