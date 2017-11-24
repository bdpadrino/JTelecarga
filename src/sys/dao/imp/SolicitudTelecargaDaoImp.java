package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.SolicitudTelecargaDao;
import sys.model.SolicitudTelecarga;
import sys.util.HibernateUtilST;

public class SolicitudTelecargaDaoImp implements SolicitudTelecargaDao {
   
	@Override
    public Integer addSolicitudTelecarga(SolicitudTelecarga e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

	@Override
    public  List<SolicitudTelecarga> listSolicitudTelecargas() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<SolicitudTelecarga> listaSolicitudTelecarga = session.createQuery("FROM SolicitudTelecarga").list();
        session.close();
        System.out.println("Found " + listaSolicitudTelecarga.size() + " SolicitudTelecarga");
        return listaSolicitudTelecarga;
    }
    
  @Override
    public  SolicitudTelecarga findSolicitudTelecargaByFolio (Integer folio) {
	    Session session = HibernateUtilST.getSessionFactory().openSession();
	    SolicitudTelecarga tel = (SolicitudTelecarga) session.createQuery("FROM SolicitudTelecarga as t WHERE t.numeroFolio = "+folio).uniqueResult();
	    //SolicitudTelecarga tel = (SolicitudTelecarga) session.createQuery("FROM solicitud WHERE numeroFolio = 1011").uniqueResult();
	    //SolicitudTelecarga tel =  (SolicitudTelecarga) session.createQuery("FROM SolicitudTelecarga as t WHERE t.numeroFolio =:numeroFolio").setParameter("numeroFolio", folio).uniqueResult();
	    session.close();
        return tel;
    }
    
    @Override
    public void modifySolicitudTelecarga(SolicitudTelecarga e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        SolicitudTelecarga nuevo = (SolicitudTelecarga) session.get(SolicitudTelecarga.class, e.getId());
        nuevo.setMarca(e.getMarca());
        nuevo.setModelo(e.getModelo());
        nuevo.setNumeroFolio(e.getNumeroFolio());
        nuevo.setTipoAplicacion(e.getTipoAplicacion());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteSolicitudTelecarga(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        SolicitudTelecarga e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public SolicitudTelecarga findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        SolicitudTelecarga e = (SolicitudTelecarga) session.get(SolicitudTelecarga.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM SolicitudTelecarga");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all SolicitudTelecarga.");

    }
    
      
}

