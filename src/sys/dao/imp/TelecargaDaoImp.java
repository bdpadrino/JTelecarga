package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.TelecargaDao;
import sys.model.Telecarga;


public class TelecargaDaoImp implements TelecargaDao {
   
    HibernateUtil hu = new HibernateUtil();

    @Override
    public Integer addTelecarga(Telecarga e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getRqtKey();
    }

    @Override
    public  List<Telecarga> listTelecargas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<Telecarga> listaTelecarga = session.createQuery("FROM Telecarga").list();
        session.close();
        System.out.println("Found " + listaTelecarga.size() + " Telecarga");
        return listaTelecarga;
    }
    
    @Override
    public void modifyTelecarga(Telecarga e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Telecarga nuevo = (Telecarga) session.get(Telecarga.class, e.getRqtKey());
        nuevo.setBnkKey(e.getBnkKey());;
        nuevo.setBnkName(e.getBnkName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteTelecarga(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Telecarga e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public Telecarga findByID(Integer id) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Telecarga e = (Telecarga) session.get(Telecarga.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Telecarga");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Telecarga.");

    }
    
      
}

