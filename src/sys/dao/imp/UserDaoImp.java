package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


import sys.dao.UserDao;
import sys.model.User;
import sys.util.HibernateUtilST;


public class UserDaoImp implements UserDao {
   
    @Override
    public Integer addUser(User e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public  List<User> listUsers() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<User> listUser = session.createQuery("FROM User").list();
        session.close();
        System.out.println("Found " + listUser.size() + " User");
        return listUser;
    }
    
    @Override
    public void modifyUser(User e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        User nuevo = (User) session.get(User.class, e.getId());
        nuevo.setUsername(e.getUsername());
        nuevo.setStatus(e.getStatus());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }
    
    @Override
    public void modifyUserStatus(User e) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        User nuevo = (User) session.get(User.class, e.getId());
        nuevo.setStatus(e.getStatus());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void deleteUser(Integer id) {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        User e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    @Override 
    public User findByID(Integer id) {   
        Session session = HibernateUtilST.getSessionFactory().openSession();
        User e = (User) session.get(User.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtilST.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all User.");

    }
    
    @Override   
    public User findByUsername(String username) {  
        Session session = HibernateUtilST.getSessionFactory().openSession();
        String query = "FROM User u WHERE u.username = '"+username+"'";
        User user = (User) session.createQuery(query).uniqueResult();
        session.close();
        return user;
    }

    
    @Override   
    public User findByUsernameActive(String username) {  
        Session session = HibernateUtilST.getSessionFactory().openSession();
        String query = "FROM User u WHERE u.username = '"+username+"' AND u.status = 'Activo' ";
        User user = (User) session.createQuery(query).uniqueResult();
        /*Query query = session.createQuery("FROM User u WHERE u.username = ':usuario' AND u.status = ':estatus'");
        query.setString("usuario", username);
        query.setString("estatus", "Activo");        
        User e = (User) query.uniqueResult();*/
        session.close();
       
        return user;
    }
	
}

