/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconivr.hibernatecrudexample.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Antonio
 */
@Entity
@Table (name="progetti")
public class Progetto {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private int id;
  
  @Column(name = "TITOLO")
  private String titolo;
  
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "progetti")
  private Set<Studente> studenti = new HashSet<>();
 
  public Progetto() {
  }
  
  public Progetto(String titolo) {
      this.titolo = titolo;
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Set<Studente> getStudenti() {
        return studenti;
    }

    public void setStudenti(Set<Studente> studenti) {
        this.studenti = studenti;
    }
    
}
