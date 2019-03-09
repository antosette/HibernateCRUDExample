/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconivr.hibernatecrudexample.app;

import com.marconivr.hibernatecrudexample.dao.StudenteDao;
import com.marconivr.hibernatecrudexample.dao.VotoDao;
import com.marconivr.hibernatecrudexample.entities.Studente;
import com.marconivr.hibernatecrudexample.entities.Voto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Antonio
 */
public class HibernateCRUDExample {
    public static void main(String[] args) throws ParseException {
        
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
       
        System.out.println("- Istanzia DAO -");
        StudenteDao studenteDao = new StudenteDao();
        VotoDao votoDao = new VotoDao();
        System.out.println("- Crea due studenti -");
        Studente studente1 = new Studente("Mario", "Rossi", "5BI", simpleDateFormat.parse("10/10/2001"));
        Studente studente2 = new Studente("Luigi", "Neri", "5BI", simpleDateFormat.parse("08/10/2001"));
        System.out.println("- Crea due voti -");
        Voto voto1 = new Voto(3, simpleDateFormat.parse("10/10/2019"), studente1);
        Voto voto2 = new Voto(4, simpleDateFormat.parse("11/10/2019"), studente1);
        System.out.println("- Salva studenti e voti nel database -");
        studenteDao.saveStudente(studente1);
        studenteDao.saveStudente(studente2);
        votoDao.saveVoto(voto1);
        votoDao.saveVoto(voto2);
        System.out.println("- Recupera studenti e relativi voti dal database -");
        List<Studente> studenti = studenteDao.getStudenti();
        studenti.forEach((s) -> {
            System.out.println(s.getCognome() + " " + s.getNome());
            s.getVoti().forEach((v) -> {
               System.out.println("IdVoto: " + v.getId() + " Data: " + v.getData() + " Voto: " + v.getVoto());
            });
        });
        
        System.out.println("- Aggiorna il primo voto per il primo studente -");
        voto1 = votoDao.getVoti().get(0);
        System.out.println("Sto modificando il voto con IdVoto: "+ voto1.getId() + " Data: " + voto1.getData() + " Voto: " + voto1.getVoto());
        voto1.setVoto(8); //cambio il voto
        votoDao.updateVoto(voto1); //aggiorno
        System.out.println("- Stampa nuovamente i voti del primo studente -");
        studente1 = studenteDao.getStudenteById(studenti.get(0).getId());
        System.out.println("Voti aggiornati per lo studente " + studente1.getCognome() + " " + studente1.getNome());
        studente1.getVoti().forEach((v) -> {
               System.out.println("IdVoto: "+ v.getId() + " Data: " + v.getData() + " Voto: " + v.getVoto());
            });
        System.out.println("- Cancella tutti gli studenti e tutti i voti dal database -");
        studenti.forEach((s) -> {
            s.getVoti().forEach((v) -> {
               votoDao.deleteVoto(v);
            });
            studenteDao.deleteStudente(s);
        });
        System.out.println("- Recupera nuovamente studenti e relativi voti dal database -");
        studenti = studenteDao.getStudenti();
        if(studenti.isEmpty()) {
            System.out.println("- Database vuoto -");
        }
        else {
            System.out.println("- Database NON vuoto -");
        }
       
    }
}
