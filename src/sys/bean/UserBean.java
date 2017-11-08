package sys.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sys.dao.imp.UserDaoImp;
import sys.dao.UserDao;
import sys.model.User;
 
@ManagedBean(name="UserBean")
@SessionScoped
public class UserBean implements Serializable{


	private static final long serialVersionUID = -344366683597788800L;
	private User user;
	UserDao cu = new UserDaoImp();
	private List<User> listUsers;
	
		
	public UserBean() {
		
	}
	
	
	@PostConstruct
    public void init() {
		System.out.println("instanciando el login");
		user = new User();

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	public List<User> getListUsers() {
		listUsers = cu.listUsers();
		System.out.println("devolvio la lsta");
		return listUsers;
	}


	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}


	public String iniciarSesion(){
		System.out.println("entro a iniciar Sesion");
		System.out.println("entro a iniciar Sesion" +user.getUsername());
				
		if (user.getUsername().equals("admin") && user.getPassword().equals("admin")){
			System.out.println("clave correcta");
			System.out.println("user" +user.getUsername() + "password" + user.getPassword());
			return "/HistorialTransacciones.xhtml?faces-redirect=true";
		}
		else {
			System.out.println("clave incorrecta");
			System.out.println("user" +user.getUsername() + "password" + user.getPassword());
			return "";
		}
		
	}
	
	public void Registrar() {
		cu.addUser(user);
	}

    
}