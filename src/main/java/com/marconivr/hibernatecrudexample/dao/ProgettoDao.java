package com.marconivr.hibernatecrudexample.dao;
/**
 *
 * @author Antonio
 */
import com.marconivr.hibernatecrudexample.configuration.HibernateConfig;
import com.marconivr.hibernatecrudexample.entities.Progetto;
import com.marconivr.hibernatecrudexample.entities.Studente;
import com.marconivr.hibernatecrudexample.entities.Voto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProgettoDao {
    
    private void operation(Progetto progetto, int queryType) {
         Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // avvia una transazione
            transaction = session.beginTransaction();
            
        switch (queryType) {
            case 1: //SAVE
                    session.save(progetto); 
                    break;
            case 2: //DELETE
                    session.delete(progetto);
                     break;
            case 3: //UPDATE
                    session.update(progetto);
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
    
    public void deleteProgetto(Progetto progetto) {
        operation(progetto, 2);
    }
     
    public void saveProgetto(Progetto progetto) {
        operation(progetto, 1);
    }
    
     public void updateProgetto(Progetto progetto) {
        operation(progetto, 3);
    }
    public List <Progetto> getProgetti() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Progetto", Progetto.class).list();//Hibernate Query Language (HQL) - info: https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm
        }
    }
}