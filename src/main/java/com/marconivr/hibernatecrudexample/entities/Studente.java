/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconivr.hibernatecrudexample.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private int id;
  
  @Column(name = "NOME")
  private String nome;
  @Column(name = "COGNOME")
  private String cognome;
  @Column(name = "CLASSE")
  private String classe;
  @Column(name = "ETA")
  private int età;
  @OneToMany(fetch = FetchType.EAGER, mappedBy="studente") //EAGER: carico i voti associati a uno studenti insieme alle altre proprietà.
  private Set<Voto> voti;
  
  public Studente() {
  }
  
  public Studente(String nome, String cognome, String classe, int età) {
      this.nome = nome;
      this.cognome = cognome;
      this.classe = classe;
      this.età = età;
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

    public int getEtà() {
        return età;
    }

    public void setEtà(int età) {
        this.età = età;
    }

    public Set<Voto> getVoti() {
        return voti;
    }

    public void setVoti(Set<Voto> voti) {
        this.voti = voti;
    }
    
}
