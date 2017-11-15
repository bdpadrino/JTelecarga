package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.BitacoraErrorDao;
import sys.model.BitacoraError;


public class BitacoraErrorDaoImp implements BitacoraErrorDao {
   
    HibernateUtil hu = new HibernateUtil();

    @Override
    public Integer addBitacoraError(BitacoraError e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<BitacoraError> listBitacoraErrors() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<BitacoraError> listaBitacoraError = session.createQuery("FROM BitacoraError").list();
        session.close();
        System.out.println("Found " + listaBitacoraError.size() + " BitacoraError");
        return listaBitacoraError;
    }
    
    @Override
    public void modifyBitacoraError(BitacoraError e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        BitacoraError nuevo = (BitacoraError) session.get(BitacoraError.class, e.getId());
        nuevo.setFecha(e.getFecha());;
        nuevo.setCodigoError(e.getCodigoError());
        nuevo.setMensajeError(e.getMensajeError());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteBitacoraError(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        BitacoraError e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public BitacoraError findByID(Integer id) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        BitacoraError e = (BitacoraError) session.get(BitacoraError.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM BitacoraError");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all BitacoraError.");

    }
    
    public String createErrorCode(String ubi, String tip, String err) {
    	String codigoError = "E_"+ubi+"_"+tip+"_"+err;
    	System.out.println("codigo de error = "+codigoError);
    	return codigoError;
    }
      
}

