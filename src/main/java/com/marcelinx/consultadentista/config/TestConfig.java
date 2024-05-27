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
public class TestConfig implements CommandLineRunner {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private DentistaRepository dentistaRepository;
    
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private AgendaRepository agendaRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
        var c1 = new Cliente(null, "Marcelino", "marcelino@gmail.com", "123456789", LocalDate.of(1990, 5, 26), "Masculino", "Rua ABC, 123");
        var d1 = new Dentista(null, "Otavio", "Geral", "1234-SP", "Rua XYZ, 456", "987654321");
        var d2 = new Dentista(null, "Carlos Roberto", "Geral", "5678-SP", "Rua UVW, 789", "654321987");
        var cc1 = new Consulta(null, LocalTime.now(), c1, d1);
        var a1 = new Agenda(null, LocalDate.of(2024, 5, 26), cc1.getCliente(), cc1.getDentista());
        
        cc1.setAgenda(a1);
    
        
        clienteRepository.saveAll(Arrays.asList(c1));
        dentistaRepository.saveAll(Arrays.asList(d1,d2));
        agendaRepository.saveAll(Arrays.asList(a1));
        consultaRepository.saveAll(Arrays.asList(cc1));
        
    }
        
}
