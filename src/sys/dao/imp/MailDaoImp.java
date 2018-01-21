package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


import sys.dao.MailDao;
import sys.model.Mail;
import sys.util.HibernateUtilST;


public class MailDaoImp implements MailDao {
   
    @Override
    public Integer addMail(Mail e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<Mail> listMails() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<Mail> listMail = session.createQuery("FROM Mail").list();
        session.close();
        System.out.println("Found " + listMail.size() + " Mail");
        return listMail;
    }
    
    @Override
    public void modifyMail(Mail e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Mail nuevo = (Mail) session.get(Mail.class, e.getId());
        nuevo.setUsername(e.getUsername());
        nuevo.setPassword(e.getPassword());
        nuevo.setHost(e.getHost());
        nuevo.setPuerto(e.getPuerto());
        nuevo.setDirEnvio(e.getDirEnvio());
        nuevo.setAsunto(e.getAsunto());
        nuevo.setCuerpo(e.getCuerpo());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }
    
    @Override
    public void deleteMail(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Mail e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override 
    public Mail findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        Mail e = (Mail) session.get(Mail.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Mail");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Mail.");

    }
    
   	
}

