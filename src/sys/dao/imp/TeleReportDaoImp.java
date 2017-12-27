package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.TeleReportDao;
import sys.model.TeleReport;
import sys.util.HibernateUtilST;

public class TeleReportDaoImp implements TeleReportDao{
   
	@Override
    public Integer addTeleReport(TeleReport e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    
    @Override
    public  List<TeleReport> listTeleReports() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<TeleReport> listaTeleReport = session.createQuery("FROM TeleReport").list();
        session.close();
        System.out.println("Found " + listaTeleReport.size() + " TeleReport");
        return listaTeleReport;
    }
    
  
    //@Override
    public void modifyTeleReport(TeleReport e) {
        /*Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        TeleReport nuevo = (TeleReport) session.get(TeleReport.class, e.getRqtKey());
        nuevo.setBnkKey(e.getBnkKey());
        nuevo.setBnkName(e.getBnkName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());*/
    }

    @Override
    public void deleteTeleReport(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        TeleReport e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public TeleReport findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        TeleReport e = (TeleReport) session.get(TeleReport.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM TeleReport");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all TeleReport.");

    }
    
      
}

