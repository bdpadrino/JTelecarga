package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


import sys.dao.UserDao;
import sys.model.User;


public class UserDaoImp implements UserDao {
   
    HibernateUtil hu = new HibernateUtil();

    @Override
    public Integer addUser(User e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<User> listUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<User> listUser = session.createQuery("FROM User").list();
        session.close();
        System.out.println("Found " + listUser.size() + " User");
        return listUser;
    }
    
    @Override
    public void modifyUser(User e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User nuevo = (User) session.get(User.class, e.getId());
        nuevo.setUsername(e.getUsername());
        nuevo.setStatus(e.getStatus());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteUser(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override 
    public User findByID(Integer id) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        User e = (User) session.get(User.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all User.");

    }
    
    @Override   
    public User findByUsername(String username) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        User e = (User) session.createQuery("FROM User where username = '"+username+"'").uniqueResult();
        session.close();
        return e;
    }

	
}

