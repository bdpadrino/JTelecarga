package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.TransactionDao;
import sys.model.TerminalInfo;

public class TerminalInfoDaoImp /*implements TerminalInfoDao */{
   
    HibernateUtil hu = new HibernateUtil();

    //@Override
    public Integer addTerminalInfo(TerminalInfo e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    //@Override
    public  List<TerminalInfo> listTerminalInfos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<TerminalInfo> listaTerminalInfo = session.createQuery("FROM TerminalInfo").list();
        session.close();
        System.out.println("Found " + listaTerminalInfo.size() + " TerminalInfo");
        return listaTerminalInfo;
    }
    
  //@Override
    public  TerminalInfo findTerminalInfoByFolio (Integer folio) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    TerminalInfo tel = (TerminalInfo) session.createQuery("FROM TerminalInfo as t WHERE t.numeroFolio = "+folio).uniqueResult();
	    //TerminalInfo tel = (TerminalInfo) session.createQuery("FROM solicitud WHERE numeroFolio = 1011").uniqueResult();
	    //TerminalInfo tel =  (TerminalInfo) session.createQuery("FROM TerminalInfo as t WHERE t.numeroFolio =:numeroFolio").setParameter("numeroFolio", folio).uniqueResult();
	    session.close();
        return tel;
    }
    
    //@Override
    public void modifyTerminalInfo(TerminalInfo e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TerminalInfo nuevo = (TerminalInfo) session.get(TerminalInfo.class, e.getId());
        nuevo.setMarca(e.getMarca());
        nuevo.setModelo(e.getModelo());
        nuevo.setNumeroFolio(e.getNumeroFolio());
        nuevo.setTerminal(e.getTerminal());
        nuevo.setTipo_aplicacion(e.getTipo_aplicacion());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

   // @Override
    public void deleteTerminalInfo(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TerminalInfo e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    //@Override
    public TerminalInfo findByID(Integer id) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        TerminalInfo e = (TerminalInfo) session.get(TerminalInfo.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM TerminalInfo");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all TerminalInfo.");

    }
    
      
}

