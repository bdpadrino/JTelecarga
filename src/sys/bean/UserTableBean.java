package sys.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
//import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.RequestScoped;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import sys.dao.imp.UserDaoImp;
import sys.dao.UserDao;
import sys.model.User;

@ManagedBean(name="userBean")
//@RequestScoped
//@SessionScoped
@ViewScoped
public class UserTableBean implements Serializable{

	private static final long serialVersionUID = -480409353442786930L;

	//@ManagedProperty("#{carService}")
	private User user;	
	private User newUser;	

	UserDao cu = new UserDaoImp();
	
	private List<User> listUsers;
		
	public UserTableBean() {
		
	}
	
	@PostConstruct
    public void init() {
		System.out.println("instancia");
		this.user = new User();
		this.newUser = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	public List<User> getListUsers() {
		listUsers = cu.listUsers();
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	 public void onRowSelect(SelectEvent event) {
		 	System.out.println("event "+((User)event.getObject()).toString());
	        FacesMessage msg = new FacesMessage("Car Selected", ((User)event.getObject()).toString());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	
	public void onRowEdit(RowEditEvent event) {
		System.out.println("EDITANDO FILA REQUEST SCOPE");
		System.out.println("OBJETO user"+user.toString());
		System.out.println("OBJETO newUser"+newUser.toString());
		User u = ((User) event.getObject()); 
		System.out.println("event "+((User)event.getObject()).toString());
		System.out.println("nuevos valores" +u.toString());
		//cu.modifyUser(user);
        FacesMessage msg = new FacesMessage("Usuario "+u.getUsername() +" Modificado " +u.getId(),"");
        FacesContext.getCurrentInstance().addMessage(":form:userDT", msg);
    }
     
	public void modifyUser() {
		System.out.println("new user"+user.toString());
		/*User u = new User();
		u =	((User) event.getObject());
		System.out.println("nuevos valores" +u.toString());*/
	}
	
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((User) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         System.out.println("soi pasa por aca");
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void deleteUser() {
		System.out.println(user.toString());
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			cu.deleteUser(user.getId());
		}
	}
    
}