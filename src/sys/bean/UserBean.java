package sys.bean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.JDBCException;

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
	Util util = new Util();

	UserDao cu = new UserDaoImp();
	private List<User> listUsers;
		
	public UserBean() {
		
	}

	@PostConstruct
    public void init() {
		this.user = new User();
		this.userR = new User();
		this.listUsers = cu.listUsers();
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
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public String iniciarSesion(){
		try {
			if (user!= null) {
				User userBD = cu.findByUsernameActive(user.getUsername());
				if (userBD != null) {
					//System.out.println("MD5 del que ingrese "+util.encriptaEnMD5((user.getPassword().trim())));
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
					FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario no Registrado o no Activo",""));
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
	
	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "/index.xhtml";
	}
		
	public void addUser() {
		System.out.println("hola registrando "+user.toString());
		
		try{
			userR.setStatus("Inactivo");
			userR.setPassword(util.encriptaEnMD5(userR.getPassword()));
			cu.addUser(userR);
			FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso",""));
			userR = new User();
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
	
}