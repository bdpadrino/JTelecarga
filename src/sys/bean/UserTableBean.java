package sys.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import sys.dao.imp.UserDaoImp;
import sys.dao.UserDao;
import sys.model.User;
import sys.util.Util;

@ManagedBean(name="userBean")
@ViewScoped
public class UserTableBean implements Serializable{

	private static final long serialVersionUID = -480409353442786930L;

	//@ManagedProperty("#{carService}")
	private User user;	
	private User userToAdd;	
	
	Util util = new Util();

	UserDao cu = new UserDaoImp();
	
	private List<User> listUsers;
		
	public UserTableBean() {
		
	}
	
	@PostConstruct
    public void init() {
		this.user = new User();
		this.userToAdd = new User();
		this.listUsers = cu.listUsers();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserToAdd() {
		return userToAdd;
	}

	public void setUserToAdd(User userToAdd) {
		this.userToAdd = userToAdd;
	}
	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	 public void onRowSelect(SelectEvent event) {
		System.out.println("Fila seleccionada");
		this.userToAdd =  ((User) event.getObject());
	 	System.out.println("event "+((User)event.getObject()).toString());
        FacesMessage msg = new FacesMessage("Car Selected", ((User)event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowEdit(RowEditEvent event) {
		try {
			User u = ((User) event.getObject()); 
			System.out.println("nuevos valores" +u.toString());
			cu.modifyUser(u);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario "+u.getUsername() +" Modificado Exitosamente",""));
		}
		catch(JDBCException e) {
			System.out.println("eror code "+e.getSQLException().getSQLState());
			if (e.getSQLException().getSQLState().equals("23000")) {
				FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_WARN,"Nombre de Usuario ya registrado",e.getMessage()));
			}
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al registrar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
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

  
    public void addUser() {
		try{
			userToAdd.setPassword(util.encriptaEnMD5(userToAdd.getPassword()));
			cu.addUser(userToAdd);
			FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso",""));
			userToAdd = new User();
		}catch(JDBCException e) {
			System.out.println("eror code "+e.getSQLException().getSQLState());
			if (e.getSQLException().getSQLState().equals("23000")) {
				FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario ya registrado",e.getMessage()));
				
			}
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al registrar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
	}
	

	public void modifyUser() {
		try 
		{
			if (user.getUsername() == cu.findByID(user.getId()).getUsername()) {
				System.out.println("Modificando solo nombre de usuario");
				cu.modifyUserStatus(user);
				FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificado Estatus Exitosamente",""));
				
			}
			else {
				//MODIFICADO USERNAME Y ESTATUS
				cu.modifyUser(user);
				FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificado Exitosamente",""));
			}
			
		}catch(JDBCException e) {
			System.out.println("eror code "+e.getSQLException().getSQLState());
			if (e.getSQLException().getSQLState().equals("23000")) {
				FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario ya registrado",e.getMessage()));
				
			}
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al registrar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
	}

	public void deleteUser() {
		try {
			//User u = ((User) event.getObject()); 
			System.out.println("Entrando a eliminar"+userToAdd.toString());
			
			if (userToAdd == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Debe Seleccionar una fila"));
			}
			else {
				String username = userToAdd.getUsername();
				cu.deleteUser(userToAdd.getId());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario "+username+" Eliminado con exito",""));
			}
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
	}
	
	public void deleteUser(User userReceived) {
		try {
			System.err.println("Entrando");
			System.out.println("Entrando a eliminar"+userReceived.toString());
			
			
				String username = userReceived.getUsername();
				cu.deleteUser(userReceived.getId());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario "+username+" Eliminado con exito",""));
				refresh();
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
	}
	
	public void refresh() {
		this.listUsers = cu.listUsers();
	}
    
}