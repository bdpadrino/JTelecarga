package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.BduDao;
import sys.model.Bdu;


public class BduDaoImp implements BduDao {
   
    HibernateUtil hu = new HibernateUtil();

    @Override
    public Integer addBdu(Bdu e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<Bdu> listBdus() {
    	System.out.println("NTRO");
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<Bdu> listaBdu = session.createQuery("FROM Bdu").list();
        session.close();
        System.out.println("Found " + listaBdu.size() + " Bdu");
        return listaBdu;
    }
    
    @Override
    public void modifyBdu(Bdu e) {
       /* Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Bdu nuevo = (Bdu) session.get(Bdu.class, e.getRqtKey());
        nuevo.setBnkKey(e.getBnkKey());;
        nuevo.setBnkName(e.getBnkName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());*/
    }

    @Override
    public void deleteBdu(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Bdu e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public Bdu findByID(Integer id) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Bdu e = (Bdu) session.get(Bdu.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Bdu");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Bdu.");

    }
    
      
}

