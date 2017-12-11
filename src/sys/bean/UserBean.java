package sys.bean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
//import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.JDBCException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import sys.dao.imp.UserDaoImp;
import sys.dao.UserDao;
import sys.model.User;
import sys.util.SessionUtils;
import sys.util.Util;
 
@ManagedBean(name="UserBean")
@SessionScoped
public class UserBean implements Serializable{


	private static final long serialVersionUID = -344366683597788800L;
	private User user;
	private User userR;
	private User userInTable;
	private User newUser;
	Util util = new Util();

	UserDao cu = new UserDaoImp();
	private List<User> listUsers;
		
	public UserBean() {
		
	}

	@PostConstruct
    public void init() {
		this.user = new User();
		this.userR = new User();
		this.userInTable = new User();
		this.newUser = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUserR() {
		return userR;
	}

	public void setUserR(User userR) {
		this.userR = userR;
	}

	public List<User> getListUsers() {
		listUsers = cu.listUsers();
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public User getUserInTable() {
		return userInTable;
	}

	public void setUserInTable(User userInTable) {
		this.userInTable = userInTable;
	}
	
	public void onRowEdit(RowEditEvent event) {
		System.out.println("EDITANDO FILA");
		System.out.println(userInTable.toString());
		User u = new User();
		u =	((User) event.getObject());
		System.out.println("nuevos valores" +u.toString());
		FacesContext context = FacesContext.getCurrentInstance();
		context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
        //FacesMessage msg = new FacesMessage("Usuario "+userInTable.getUsername() +" Modificado", ""+((User) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
	
	
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((User) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         System.out.println("old" +oldValue.toString() + " nuevo "+newValue.toString());
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    
    public void modificar(User newUser) {
		System.out.println("new user"+newUser.toString());
	}
    
	public String iniciarSesion(){
		try {
			if (user!= null) {
				User userBD = cu.findByUsername(user.getUsername());
				if (userBD != null) {
					System.out.println("MD5 del que ingrese "+util.encriptaEnMD5((user.getPassword().trim())));
					if (util.encriptaEnMD5((user.getPassword().trim())).equals(userBD.getPassword())){
						System.out.println("clave correcta user" +user.getUsername() + "password " + user.getPassword());
						HttpSession session = SessionUtils.getSession();
						session.setAttribute("username", user);
						return "/Home.xhtml?faces-redirect=true";
					}
					else {
						System.out.println("clave incorrecta user " +user.getUsername() + "password " + user.getPassword());
						FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN,"Credenciales Erroneas",""));
						return "";
					}
				}
				else {
					FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario no registrado",""));
					return "";
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Valor de usuario es nulo",""));
				return "";
			}
		}
		catch(NoSuchAlgorithmException e) {
			System.out.println("Mensaje: "+e.getMessage());
			System.out.println("Causa: "+e.getCause());
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),""));
			return "";
		}
			
	}
	
	public void addUser() {
		System.out.println("hola registrando "+user.toString());
		
		try{
			userR.setStatus("Inactivo");
			userR.setPassword(util.encriptaEnMD5(userR.getPassword()));
			cu.addUser(userR);
			FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso",""));
			userR.setUsername("");
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
	
	public void modifyUser() {
		System.out.println("ENTRO AAQUI");
		System.out.println("userInTable "+userInTable.toString());
	}
	
	
	/*public void modifyUser() {
		try {
			System.out.println("new user"+userInTable.toString());
			if (userInTable.getUsername() == cu.findByID(userInTable.getId()).getUsername()) {
				System.out.println("Modificando solo nombre de usuario");
				cu.modifyUserStatus(userInTable);
				FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificado Estatus Exitosamente",""));
				
			}
			else {
				//MODIFICADO USERNAME Y ESTATUS
				cu.modifyUser(userInTable);
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
	}*/

	public void deleteUser() {
		System.out.println(userInTable.toString());
		if (userInTable == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			cu.deleteUser(userInTable.getId());
		}
	}
	
	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/index.xhtml";
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	
	
    
}