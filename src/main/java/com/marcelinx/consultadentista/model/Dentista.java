package com.marcelinx.consultadentista.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_dentistas")
public final class Dentista implements Serializable  {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 200, nullable = false, name = "ds_nome")
	private String name;

	@Column(length = 12, nullable = false, name = "ds_categoria")
	private String category;

	@OneToMany(mappedBy = "dentista")
	private List<Agenda> agendas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "dentista")
	private List<Consulta> consultas;
	
	public Dentista() {
	};

	public Dentista(Long id, String name, String category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public void adicionarAgenda(Agenda agendas) {
		this.agendas.add(agendas);
	}
	
	public void adicionarConsulta(Consulta consulta) {
		this.consultas.add(consulta);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		Dentista other = (Dentista) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Dentista [id=" + id + ", name=" + name + ", category=" + category + ", agendas=" + agendas
				+ ", consultas=" + consultas + "]";
	}
	
	

}