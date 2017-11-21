package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.TransactionDao;
import sys.model.Transaction;
import sys.util.HibernateUtilST;


public class TransactionDaoImp implements TransactionDao {
   
    //HibernateUtilST hu = new HibernateUtilST();

    @Override
    public Integer addTransaction(Transaction e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getSystems_trace_number();
    }

    @Override
    public  List<Transaction> listTransactions() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<Transaction> listaTransaction = session.createQuery("FROM Transaction").list();
        session.close();
        System.out.println("Found " + listaTransaction.size() + " Transaction");
        return listaTransaction;
    }
    
    @Override
    public void modifyTransaction(Transaction e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Transaction nuevo = (Transaction) session.get(Transaction.class, e.getSystems_trace_number());
        nuevo.setModel(e.getModel());;
        nuevo.setPan(e.getPan());
        nuevo.setCard_holder(e.getCard_holder());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteTransaction(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Transaction e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override
    public Transaction findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        Transaction e = (Transaction) session.get(Transaction.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Transaction");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Transaction.");

    }
    
      
}

