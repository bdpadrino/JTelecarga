package sys.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.event.RowEditEvent;

import sys.dao.imp.UserDaoImp;
import sys.dao.UserDao;
import sys.model.User;
import sys.util.Util;

@ManagedBean(name="userBean")
@ViewScoped
public class UserTableBean implements Serializable{

	private static final long serialVersionUID = -480409353442786930L;

	private User user;	
	Util util = new Util();
	UserDao cu = new UserDaoImp();
	private List<User> listUsers;
		
	public UserTableBean() {
		
	}
	
	@PostConstruct
    public void init() {
		this.user = new User();
		this.listUsers = cu.listUsers();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void onRowEdit(RowEditEvent event) {
		User u = ((User) event.getObject()); 
		modifyUser(u);
	}
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((User) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  
    /**
     * METODO USADO PARA REGISTRAR UN USUARIO EN BD
     */
    public void addUser() {
    	try{
    		user.setPassword(util.encriptaEnMD5(user.getPassword()));
    		cu.addUser(user);
    		this.listUsers.add(user);
    		FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario "+user.getUsername()+" Agregado Exitosamente",""));
    		this.user = new User();
    	}
    	catch(JDBCException e) {
    		System.out.println("Sql State "+e.getSQLException().getSQLState());
    		System.out.println("Eror code "+e.getSQLException().getErrorCode());
    		//UNIQUE CONSTRAINT ERROR CODE 1
    		if (e.getSQLException().getErrorCode() == 1) {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Nombre de usuario ya existe",e.getMessage()));
    		}
    		//MANDATORIEDAD ERROR CODE 1400
    		if (e.getSQLException().getErrorCode() == 1400) {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Campo que no puede ser nulo",e.getMessage()));
    		}
    	}
    	catch(Exception e) {
    		FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al registrar",e.getMessage()));
    		System.out.println("Mensaje EX"+e.getMessage());
    		System.out.println("Causa   EX"+e.getCause());
    	}
    }
	
    /**
     * METODO USADO PARA MODIFICAR USUARIOS EN BD
     * @param userReceived
     */
    public void modifyUser(User userReceived) {
		try {
			cu.modifyUser(userReceived);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario "+userReceived.getUsername() +" Modificado Exitosamente",""));
		}
		catch(JDBCException e) {
			System.out.println("Sql State "+e.getSQLException().getSQLState());
    		System.out.println("Eror code "+e.getSQLException().getErrorCode());
    		//UNIQUE CONSTRAINT ERROR CODE 1
    		if (e.getSQLException().getErrorCode() == 1) {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Nombre de usuario ya existe",e.getMessage()));
    		}
    		//MANDATORIEDAD ERROR CODE 1400
    		if (e.getSQLException().getErrorCode() == 1400) {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Campo que no puede ser nulo",e.getMessage()));
    		}
    		//MANDATORIEDAD CAMPO A MODIFICAR NO PUEDE SER NULO ERROR CODE 1407
    		if (e.getSQLException().getErrorCode() == 1407) {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Campo a modificar no puede ser nulo",e.getMessage()));
    		}
    	}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al registrar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
    }
    
    /**
     * METODO USADO PARA ELIMINAR UN USUARIO EN BD
     * @param userReceived
     */
	public void deleteUser(User userReceived) {
		try {
			String username = userReceived.getUsername();
			cu.deleteUser(userReceived.getId());
			this.listUsers.remove(userReceived);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario "+username+" Eliminado con exito",""));
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
	}
	
    
}