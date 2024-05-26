package com.marcelinx.consultadentista.model;

import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 200, nullable = false, name = "dh_hora")
	private LocalTime  hora;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "fk_cliente")
	private Cliente cliente;
	
	@ManyToOne(optional = false)
	@JoinColumn( name = "fk_dentista")
	private Dentista dentista;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "fk_agenda")
	private Agenda agenda;
	
	public Consulta(Long id, LocalTime hora, Cliente cliente, Dentista dentista) {
		this.id = id;
		this.hora = hora;
		this.dentista = dentista;
		this.cliente = cliente;
	}

	public Consulta() {
	}

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public LocalTime getLocalTime() {
		return hora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public void setLocalTime(LocalTime hora) {
		this.hora = hora;
	}

	
	@Override
	public String toString() {
		return "Consulta [id=" + id + ", hora=" + hora +  "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return Objects.equals(id, other.id);
	}
	
}
