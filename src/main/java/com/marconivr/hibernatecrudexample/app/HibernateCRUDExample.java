/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconivr.hibernatecrudexample.app;

import com.marconivr.hibernatecrudexample.configuration.HibernateConfig;
import com.marconivr.hibernatecrudexample.dao.ProgettoDao;
import com.marconivr.hibernatecrudexample.dao.StudenteDao;
import com.marconivr.hibernatecrudexample.dao.VotoDao;
import com.marconivr.hibernatecrudexample.entities.Progetto;
import com.marconivr.hibernatecrudexample.entities.Studente;
import com.marconivr.hibernatecrudexample.entities.Voto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author Antonio
 */
public class HibernateCRUDExample {
    public static void main(String[] args) throws ParseException {
        
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
       
        System.out.println("- Istanzia DAOs -");
        StudenteDao studenteDao = new StudenteDao();
        VotoDao votoDao = new VotoDao();
        ProgettoDao progettoDao = new ProgettoDao();
        System.out.println("- Crea due studenti -");
        Studente studente1 = new Studente("Mario", "Rossi", "5BI", simpleDateFormat.parse("10/10/2001"));
        Studente studente2 = new Studente("Luigi", "Neri", "5BI", simpleDateFormat.parse("08/10/2001"));
        System.out.println("- Crea due voti -");
        Voto voto1 = new Voto(3, simpleDateFormat.parse("10/10/2019"), studente1);
        Voto voto2 = new Voto(4, simpleDateFormat.parse("11/10/2019"), studente1);
        System.out.println("- Crea tre progetti -");
        Progetto progetto1 = new Progetto("Area Lab");
        Progetto progetto2 = new Progetto("Sicurezza Marconi");
        Progetto progetto3 = new Progetto("Olimpiadi di Informatica");
        System.out.println("- Salva progetti nel database -");
        progettoDao.saveProgetto(progetto1);
        progettoDao.saveProgetto(progetto2);
        progettoDao.saveProgetto(progetto3);
        System.out.println("- Imposta progetti per lo studente1 -");
        Set<Progetto> progettiStudente1 = new HashSet<>();
        progettiStudente1.add(progetto1);
        progettiStudente1.add(progetto2);
        studente1.setProgetti(progettiStudente1);
        System.out.println("- Imposta progetti per lo studente2 -");
        Set<Progetto> progettiStudente2 = new HashSet<>();
        progettiStudente2.add(progetto2);
        progettiStudente2.add(progetto3);
        studente2.setProgetti(progettiStudente2);
        System.out.println("- Salva studenti, associazione progetti e voti nel database -");
        studenteDao.saveStudente(studente1);
        studenteDao.saveStudente(studente2);
        votoDao.saveVoto(voto1);
        votoDao.saveVoto(voto2);
        System.out.println("- Aggiorna il primo voto per il primo studente -");
        voto1 = votoDao.getVoti().get(0);
        System.out.println("Sto modificando il voto con IdVoto: "+ voto1.getId() + " Data: " + voto1.getData() + " Voto: " + voto1.getVoto());
        voto1.setVoto(8); //cambio il voto
        votoDao.updateVoto(voto1); //aggiorno
        System.out.println("- Stampa nuovamente i voti del primo studente -");
        List<Studente> studenti = studenteDao.getStudenti();
        studente1 = studenteDao.getStudenteById(studenti.get(0).getId());
           System.out.println("- Recupera studenti, progetti e relativi voti dal database -");  
        studenti.forEach((s) -> {
            System.out.println(s.getCognome() + " " + s.getNome());
            s.getVoti().forEach((v) -> {
               System.out.println("IdVoto: " + v.getId() + " Data: " + v.getData() + " Voto: " + v.getVoto());
            });
             s.getProgetti().forEach((p) -> {
               System.out.println("IdProgetto: " + p.getId() + " Titolo: " + p.getTitolo());
            });
        });
        System.out.println("- Recupera progetti e studenti iscritti -");
        List<Progetto> progetti  = progettoDao.getProgetti();
        progetti.forEach((p) -> {
            System.out.println("IdProgetto:" + p.getId() + " Titolo:" + p.getTitolo() + " - Studenti iscritti: ");
            p.getStudenti().forEach((s) -> {
               System.out.println("IdStudente: " + s.getId() + " Cognome: " + s.getCognome() + " Nome: " + s.getNome());
            });
         
        });
        System.out.println("Voti aggiornati per lo studente " + studente1.getCognome() + " " + studente1.getNome());
        studente1.getVoti().forEach((v) -> {
               System.out.println("IdVoto: "+ v.getId() + " Data: " + v.getData() + " Voto: " + v.getVoto());
            });
        pulisciDB(studenti, votoDao, progettoDao, studenteDao); //commentare per non cancellare il database
        HibernateConfig.closeSession(); 
    }

    private static void pulisciDB(List<Studente> studenti, VotoDao votoDao, ProgettoDao progettoDao, StudenteDao studenteDao) {
        System.out.println("- Pulisci DB -");
        studenti.forEach((s) -> {
            s.getVoti().forEach((v) -> {
                votoDao.deleteVoto(v);
            });
            s.setProgetti(null);
            studenteDao.updateStudente(s);
            studenteDao.deleteStudente(s);
        });
        progettoDao.getProgetti().forEach((p) -> {
                progettoDao.deleteProgetto(p);
            });
        System.out.println("- Recupera nuovamente studenti, progetti e voti dal database -");
        studenti = studenteDao.getStudenti();
        if(studenti.isEmpty()) {
            System.out.println("- Database vuoto -");
        }
        else {
            System.out.println("- Database NON vuoto -");
        }
    }
}
