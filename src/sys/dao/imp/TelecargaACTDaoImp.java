package sys.dao.imp;

import java.util.List;
import org.hibernate.Session;
import sys.dao.TelecargaACTDao;
import sys.model.TelecargaACT;
import sys.util.HibernateUtilACT;

public class TelecargaACTDaoImp implements TelecargaACTDao {
   
    //HibernateUtilACT hu = new HibernateUtilACT();

    @Override
    public Integer addTelecarga(TelecargaACT e) {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getRqtKey();
    }

    @Override
    public  List<TelecargaACT> listTelecargas() {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<TelecargaACT> listaTelecarga = session.createQuery("FROM TelecargaACT").list();
        session.close();
        System.out.println("Found " + listaTelecarga.size() + " Telecarga");
        return listaTelecarga;
    }
    
    
    @Override
    public void modifyTelecarga(TelecargaACT e) {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        TelecargaACT nuevo = (TelecargaACT) session.get(TelecargaACT.class, e.getRqtKey());
        nuevo.setBnkKey(e.getBnkKey());
        nuevo.setBnkName(e.getBnkName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteTelecarga(Integer id) {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        TelecargaACT e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public TelecargaACT findByID(Integer id) {   
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        TelecargaACT e = (TelecargaACT) session.get(TelecargaACT.class, id);
        session.close();
        return e;
    }
   
    
    public  TelecargaACT findTelecargaByTerminal (String terminal) {
	    Session session = HibernateUtilACT.getSessionFactory().openSession();
	    TelecargaACT tel = (TelecargaACT) session.createQuery("FROM TelecargaACT as t WHERE t.terId = "+terminal+"").uniqueResult();
        session.close();
        return tel;
    }
    
    public  TelecargaACT findTelecargaByFolio (Integer folio) {
	    Session session = HibernateUtilACT.getSessionFactory().openSession();
	    TelecargaACT tel = (TelecargaACT) session.createQuery("FROM TelecargaACT as t WHERE t.tcFolio = "+folio+"").uniqueResult();
        session.close();
        return tel;
    }
   
    /*public void deleteAll() {
        Session session = HibernateUtilACT.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM TelecargaACT");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all TelecargaACT.");
    }*/
    
      
}

