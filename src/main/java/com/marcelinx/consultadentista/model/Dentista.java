package com.marcelinx.consultadentista.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(length = 50, nullable = false, name = "ds_categoria")
    private String category;

    @Column(length = 12, nullable = false, name = "ds_cro")
    private String cro;
    
    @Column(length = 200, nullable = false, name = "ds_endereco")
    private String endereco;
    
    @Column(length = 20, nullable = false, name = "ds_telefone")
    private String telefone;
    
    public Dentista() {
    }

    public Dentista(Long id, String name, String category, String cro, String endereco, String telefone) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cro = cro;
        this.endereco = endereco;
        this.telefone = telefone;
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

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

}
