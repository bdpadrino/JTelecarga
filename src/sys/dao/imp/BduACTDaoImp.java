package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import sys.dao.BduACTDao;
import sys.model.BduACT;
import sys.util.HibernateUtilACT;

public class BduACTDaoImp implements BduACTDao {
   

    @Override
    public Integer addBduACT(BduACT e) {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<BduACT> listBduACTs() {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<BduACT> listaBduACT = session.createQuery("FROM BduACT").list();
        session.close();
        System.out.println("Found " + listaBduACT.size() + " BduACT");
        return listaBduACT;
    }
    
    @Override
    public  BduACT findBduACTByFolioInACT(Integer folio) {
        Session session = HibernateUtilACT.getSessionFactory().openSession();	
        BduACT bdu = (BduACT)session.createQuery("FROM BduACT as b WHERE b.tcFolio = "+folio+"").uniqueResult();
        session.close();
        System.out.println("Found " + bdu.toString() + " BduACT");
        return bdu;
    }
    
    @Override
    public void modifyBduACT(BduACT e) {
       /* Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        BduACT nuevo = (BduACT) session.get(BduACT.class, e.getRqtKey());
        nuevo.setBnkKey(e.getBnkKey());;
        nuevo.setBnkName(e.getBnkName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());*/
    }

    @Override
    public void deleteBduACT(Integer id) {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        BduACT e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public BduACT findByID(Integer id) {   
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        BduACT e = (BduACT) session.get(BduACT.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM BduACTACT");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all BduACT.");

    }
    
      
}

