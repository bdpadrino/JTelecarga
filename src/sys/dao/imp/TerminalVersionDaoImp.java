package sys.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import sys.model.TerminalVersion;
import sys.util.HibernateUtilST;

public class TerminalVersionDaoImp {

	 HibernateUtilST hu = new HibernateUtilST();

    //@Override
    public Integer addTerminalVersion(TerminalVersion e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    //@Override
    public  List<TerminalVersion> listTerminalVersions() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<TerminalVersion> listaTerminalVersion = session.createQuery("FROM TerminalVersion").list();
        session.close();
        System.out.println("Found " + listaTerminalVersion.size() + " TerminalVersion");
        return listaTerminalVersion;
    }
 
    //@Override
   /* public void modifyTerminalVersion(TerminalVersion e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        TerminalVersion nuevo = (TerminalVersion) session.get(TerminalVersion.class, e.getId());
        nuevo.setMarca(e.getMarca());
        nuevo.setModelo(e.getModelo());
        nuevo.setNumeroFolio(e.getNumeroFolio());
        nuevo.setTerminal(e.getTerminal());
        nuevo.setTipo_aplicacion(e.getTipo_aplicacion());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }*/

   // @Override
    public void deleteTerminalVersion(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        TerminalVersion e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    //@Override
    public TerminalVersion findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        TerminalVersion e = (TerminalVersion) session.get(TerminalVersion.class, id);
        session.close();
        return e;
    }
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM TerminalVersion");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all TerminalVersion.");

    }

    public Double findLastVersionOfModel (String modelo, String tipoApp) {
	    Session session = HibernateUtilST.getSessionFactory().openSession();
	    Double tel = (Double) session.createQuery("SELECT MAX(t.version) FROM TerminalVersion as t WHERE t.tipo_aplicacion = '"+tipoApp+"' AND t.modelo = '"+modelo+"'").uniqueResult();
  	    session.close();
        return tel;
    }
}
