package sys.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import sys.dao.imp.UserDaoImp;
import sys.dao.UserDao;
import sys.model.User;

@ManagedBean(name="userBean")
@ViewScoped
public class UserBean3 implements Serializable{

	private static final long serialVersionUID = -480409353442786930L;

	private User user;
	
	UserDao cu = new UserDaoImp();
	private List<User> listUsers;
		
	public UserBean3() {
		
	}
	
	@PostConstruct
    public void init() {
		System.out.println("instancia");
		this.user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListUsers() {
		System.out.println("buscando");
		listUsers = cu.listUsers();
		System.out.println("listUsers"+listUsers.size());
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void onRowEdit(RowEditEvent event) {
		System.out.println("EDITANDO FILA");
		System.out.println(user.toString());
		User u = new User();
		u =	((User) event.getObject());
		System.out.println("nuevos valores" +u.toString());
		//cu.modifyUser(user);
        FacesMessage msg = new FacesMessage("Usuario "+user.getUsername() +" Modificado", ""+((User) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
	public void modificarB() {
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