package com.marconivr.hibernatecrudexample.dao;
/**
 *
 * @author Antonio
 */
import com.marconivr.hibernatecrudexample.configuration.HibernateConfig;
import com.marconivr.hibernatecrudexample.entities.Studente;
import com.marconivr.hibernatecrudexample.entities.Voto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VotoDao {
    
    private void operation(Voto voto, int queryType) {
         Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // avvia una transazione
            transaction = session.beginTransaction();
            
        switch (queryType) {
            case 1: //SAVE
                    session.save(voto); 
                    break;
            case 2: //DELETE
                    session.delete(voto);
                     break;
            case 3: //UPDATE
                    session.update(voto);
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
    
    public void deleteVoto(Voto voto) {
        operation(voto, 2);
    }
     
    public void saveVoto(Voto voto) {
        operation(voto, 1);
    }
    
     public void updateVoto(Voto voto) {
        operation(voto, 3);
    }
    public List <Voto> getVoti() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Voto", Voto.class).list();//Hibernate Query Language (HQL) - info: https://www.tutorialspoint.com/hibernate/hibernate_query_language.htm
        }
    }
}