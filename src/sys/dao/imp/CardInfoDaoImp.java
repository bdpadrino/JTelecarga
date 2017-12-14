package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.CardInfoDao;
import sys.model.CardInfo;
import sys.util.HibernateUtilST;


public class CardInfoDaoImp implements CardInfoDao{
   
    
    @Override
    public Integer addCardInfo (CardInfo e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<CardInfo> listCardInfos() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<CardInfo> listCardInfo = session.createQuery("FROM CardInfo").list();
        session.close();
        System.out.println("Found " + listCardInfo.size() + " CardInfo");
        return listCardInfo;
    }
    
    @Override
    public void modifyCardInfo(CardInfo e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        CardInfo nuevo = (CardInfo) session.get(CardInfo.class, e.getId());
        nuevo.setPan(e.getPan());
        nuevo.setCard_holder(e.getCard_holder());
        nuevo.setIssuer(e.getIssuer());
        nuevo.setDate_expiration(e.getDate_expiration());
        
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteCardInfo(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        CardInfo e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public CardInfo findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        CardInfo e = (CardInfo) session.get(CardInfo.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM CardInfo");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all CardInfo.");

    }
    
      
}

