package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.TransactionDao2;
import sys.model.Transaction2;
import sys.util.HibernateUtilST;


public class TransactionDaoImp2 implements TransactionDao2{
   
    
    @Override
    public Integer addTransaction (Transaction2 e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getSystems_trace_number();
    }

    @Override
    public  List<Transaction2> listTransactions() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<Transaction2> listTransaction2 = session.createQuery("FROM Transaction2").list();
        session.close();
        System.out.println("Found " + listTransaction2.size() + " Transaction2");
        return listTransaction2;
    }
    
    @Override
    public void modifyTransaction(Transaction2 e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Transaction2 nuevo = (Transaction2) session.get(Transaction2.class, e.getSystems_trace_number());
        nuevo.setTransaction_type(e.getTransaction_type());
        nuevo.setPos_entry_mode(e.getPos_entry_mode());
        nuevo.setModel(e.getModel());
        nuevo.setCard_acceptor_terminal_id(e.getCard_acceptor_terminal_id());
        nuevo.setCard_acceptor_name(e.getCard_acceptor_name());
        nuevo.setIso(e.getIso());
        nuevo.setCurrency(e.getCurrency());
        nuevo.setPlace(e.getPlace());
        nuevo.setDate_transaction(e.getDate_transaction());
        nuevo.setTime_transaction(e.getTime_transaction());
        
       /* nuevo.setPan(e.getPan());
        nuevo.setCard_holder(e.getCard_holder());*/
        
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteTransaction(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Transaction2 e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public Transaction2 findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        Transaction2 e = (Transaction2) session.get(Transaction2.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Transaction2");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Transaction2.");

    }
    
      
}

