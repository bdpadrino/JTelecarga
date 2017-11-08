package sys.dao.imp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import sys.dao.EmpleadoDao;
import sys.model.Empleado;


public class EmpleadoDaoImp implements EmpleadoDao {
   
    HibernateUtil hu = new HibernateUtil();

    @Override
    public Integer agregarEmpleado(Empleado e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e); 
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getCedula();
    }

    @Override
    public  List<Empleado> listarEmpleados() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")			
        List<Empleado> listaEmpleado = session.createQuery("FROM Empleado order by nombre").list();
        session.close();
        System.out.println("Found " + listaEmpleado.size() + " Empleado");
        return listaEmpleado;
    }
    
    @Override
    public void modificarEmpleado(Empleado e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Empleado nuevo = (Empleado) session.get(Empleado.class, e.getCedula());
        nuevo.setNombre(e.getNombre());
        nuevo.setApellido(e.getApellido());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + e.toString());
    }

    @Override
    public void eliminarEmpleado(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Empleado e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + e.toString());
    }

    public Empleado findByID(Integer id) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Empleado e = (Empleado) session.get(Empleado.class, id);
        session.close();
        return e;
    }
   
   
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Empleado");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all Empleado.");

    }
    
       
     public Empleado findByName(String nombreCompleto) {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Empleado emp = (Empleado) session.createQuery("FROM Empleado where (nombre || ' ' || apellido) = '"+nombreCompleto+"'").uniqueResult();
        session.close();
        return emp;
    }

	
}

