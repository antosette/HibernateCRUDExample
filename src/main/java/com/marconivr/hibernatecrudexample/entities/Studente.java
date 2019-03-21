/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconivr.hibernatecrudexample.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Antonio
 */
@Entity
@Table (name="studenti")
public class Studente {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  
  @Column(name = "NOME")
  private String nome;
  @Column(name = "COGNOME")
  private String cognome;
  @Column(name = "CLASSE")
  private String classe;
  @Column(name = "DATA_DI_NASCITA")
  private Date dataDiNascita;
  @OneToMany(fetch = FetchType.EAGER, mappedBy="studente") //EAGER: carico i voti associati a uno studenti insieme alle altre proprietà.
  private Set<Voto> voti;
  
  @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
  @JoinTable(
       name = "studenti_progetti", 
       joinColumns = { @JoinColumn(name = "ID_STUDENTI") }, 
       inverseJoinColumns = { @JoinColumn(name = "ID_PROGETTI") }
   )
  Set<Progetto> progetti = new HashSet<>();
  
  public Studente() {
  }
  
  public Studente(String nome, String cognome, String classe, Date dataDiNascita) {
      this.nome = nome;
      this.cognome = cognome;
      this.classe = classe;
      this.dataDiNascita = dataDiNascita;
  }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setEtà(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public Set<Voto> getVoti() {
        return voti;
    }

    public void setVoti(Set<Voto> voti) {
        this.voti = voti;
    }

    public Set<Progetto> getProgetti() {
        return progetti;
    }

    public void setProgetti(Set<Progetto> progetti) {
        this.progetti = progetti;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studente)) return false;
        return id != null && id.equals(((Studente) o).id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
    
    
}
