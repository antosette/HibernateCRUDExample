package com.marconivr.hibernatecrudexample.dao;
/**
 *
 * @author Antonio
 */
import com.marconivr.hibernatecrudexample.configuration.HibernateConfig;
import com.marconivr.hibernatecrudexample.entities.Studente;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudenteDao {
    
     private void operation(Studente studente, int queryType) {
            Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // avvia una transazione
            transaction = session.beginTransaction();
            switch (queryType) {
            case 1: //SAVE
                    session.save(studente); // salva l'oggetto studente nel database convertendolo in una tupla nella relativa tabella
                     break;
            case 2: //DELETE
                    session.delete(studente);
                     break;
            case 3: //UPDATE
                    session.update(studente);
                     break;
            default: throw new Exception ("Invalid queryType");
        }
            // commit transazione
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }       
     }
    
    public void saveStudente(Studente studente) {
        operation(studente, 1);
    }
    
     public void deleteStudente(Studente studente) {
        operation(studente, 2);
    }
     
    public void updateStudente(Studente studente) {
        operation(studente, 3);
    }
    
   public Studente getStudenteById(int id) {
       Studente studente = null;
     try {
            Session session = HibernateConfig.getSessionFactory().openSession();
             studente =  (Studente) session.get(Studente.class, id);
            return studente;
        } catch (Exception e) {
            e.printStackTrace();
        }
     return studente;
   }
   
    public List <Studente> getStudenti() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Studente", Studente.class).list();
        }
    }
}