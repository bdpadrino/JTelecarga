package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.BitacoraErrorDao;
import sys.model.BitacoraError;
import sys.util.HibernateUtilST;


public class BitacoraErrorDaoImp implements BitacoraErrorDao {
   
    HibernateUtilST hu = new HibernateUtilST();

    @Override
    public Integer addBitacoraError(BitacoraError e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<BitacoraError> listBitacoraErrors() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<BitacoraError> listaBitacoraError = session.createQuery("FROM BitacoraError").list();
        session.close();
        System.out.println("Found " + listaBitacoraError.size() + " BitacoraError");
        return listaBitacoraError;
    }
    
    @Override
    public void modifyBitacoraError(BitacoraError e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
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
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        BitacoraError e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public BitacoraError findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        BitacoraError e = (BitacoraError) session.get(BitacoraError.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM BitacoraError");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all BitacoraError.");

    }
    
   
      
}

