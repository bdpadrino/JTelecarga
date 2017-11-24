package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.TelecargaSTDao;
import sys.model.TelecargaST;
import sys.util.HibernateUtilST;

public class TelecargaSTDaoImp implements TelecargaSTDao{
   
    @Override
    public Integer addTelecarga(TelecargaST e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<TelecargaST> listTelecargas() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<TelecargaST> listaTelecarga = session.createQuery("FROM TelecargaST").list();
        session.close();
        System.out.println("Found " + listaTelecarga.size() + " TelecargaST");
        return listaTelecarga;
    }
    
    @Override
    public  TelecargaST findTelecargaByTerminal (String terminal) {
	    Session session = HibernateUtilST.getSessionFactory().openSession();
	    TelecargaST tel = (TelecargaST) session.createQuery("FROM TelecargaST as t WHERE t.terId = "+terminal+"").uniqueResult();
        session.close();
        return tel;
    }
    
    @Override
    public  TelecargaST findTelecargaByFolio (Integer folio) {
	    Session session = HibernateUtilST.getSessionFactory().openSession();
	    TelecargaST tel = (TelecargaST) session.createQuery("FROM TelecargaST as t WHERE t.tcFolio = "+folio+"").uniqueResult();
        session.close();
        return tel;
    }
    
    
    @Override
    public void modifyTelecarga(TelecargaST e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        TelecargaST nuevo = (TelecargaST) session.get(TelecargaST.class, e.getId());
        nuevo.setTelmexPhoneNumber(e.getTelmexPhoneNumber());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteTelecarga(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        TelecargaST e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public TelecargaST findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        TelecargaST e = (TelecargaST) session.get(TelecargaST.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM TelecargaST");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Telecarga in ST.");
    }
    
      
}

