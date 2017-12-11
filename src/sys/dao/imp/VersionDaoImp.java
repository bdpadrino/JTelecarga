package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


import sys.dao.VersionDao;
import sys.model.Version;
import sys.util.HibernateUtilST;


public class VersionDaoImp implements VersionDao {
   
    @Override
    public Integer addVersion(Version e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<Version> listVersions() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<Version> listVersion = session.createQuery("FROM Version").list();
        session.close();
        System.out.println("Found " + listVersion.size() + " Version");
        return listVersion;
    }
    
    @Override
    public void modifyVersion(Version e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Version nuevo = (Version) session.get(Version.class, e.getId());
        nuevo.setTipoAplicacion(e.getTipoAplicacion());
        nuevo.setModelo(e.getModelo());
        nuevo.setVersion(e.getVersion());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteVersion(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Version e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override 
    public Version findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        Version e = (Version) session.get(Version.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Version");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Version.");

    }
 
}

