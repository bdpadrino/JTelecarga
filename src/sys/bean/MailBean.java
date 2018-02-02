package sys.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.event.RowEditEvent;

import sys.dao.imp.UserDaoImp;
import sys.dao.UserDao;
import sys.dao.imp.MailDaoImp;
import sys.dao.MailDao;
import sys.model.Mail;
import sys.model.User;
import sys.util.Util;


 
@ManagedBean(name="mailBean")
@SessionScoped
public class MailBean implements Serializable{


	private static final long serialVersionUID = -344366683597788800L;
	
	private User user;
	private Mail mail;
	Util util = new Util();

	UserDao cu = new UserDaoImp();
	MailDao cm = new MailDaoImp();
	private List<Mail> listMails;
	//Booleano que sirve para mostrar el formulario de Inicio de Sesion o de recuperacion de contrasena
	Boolean mostrar;
		
	public MailBean() {
		
	}

	@PostConstruct
    public void init() {
		this.mail = new Mail();
		this.user = new User();
		this.listMails = cm.listMails();
		this.mostrar = false;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public List<Mail> getListMails() {
		return listMails;
	}

	public void setListMails(List<Mail> listMails) {
		this.listMails = listMails;
	}
	
	public User getUser() {
		return user;
	}

	public Boolean getMostrar() {
		return mostrar;
	}

	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void onRowEdit(RowEditEvent event) {
		Mail u = ((Mail) event.getObject()); 
		modifyMail(u);
	}
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((Mail) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
    /**
     * METODO USADO PARA MODIFICAR USUARIOS EN BD
     * @param mailReceived
     */
    public void modifyMail(Mail mailReceived) {
		try {
			cm.modifyMail(mailReceived);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario "+mailReceived.getUsername() +" Modificado Exitosamente",""));
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
	 * METODO USADO CUANDO SE OLVIDA LA CONTRASENA
	 */
	public void forgotPassword(){
		Util util = new Util();
		try{
			User userBD = cu.findByUsername(user.getUsername());
			if (userBD != null) {
				String newPassword = util.generatePassword();
				userBD.setPassword(util.encriptWithMD5(newPassword));
				if(userBD.getEmail().equals(user.getEmail())) {			
					System.out.println("email iguales");
					Mail mail = cm.findByID(1);
					if (mail == null)
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"No hay configuracion de envio de correos registrada",""));
					else {
						util.sendMailSSLAcorde(mail.getUsername().trim(), mail.getPassword().trim(),mail.getHost().trim(), mail.getPuerto(), mail.getAsunto(), mail.getCuerpo() + " "+ newPassword,  mail.getDirEnvio(), user.getEmail());
						//util.sendMailSSLGmail("bdpadrino@gmail.com", "Adrian280613.", "testPass", "Cambio de clave", "bdpadrino@gmail.com");
						System.out.println("Correo Enviado");
						cu.modifyUser(userBD);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correo enviado con nueva contraseña",""));
					}
				}
				else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Correo no coincide con el registrado",""));
				}
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Usuario no registrado",""));
				
			}
		}
		catch(JDBCException e) {
			System.out.println("Sql State "+e.getSQLException().getSQLState());
			System.out.println("Eror code "+e.getSQLException().getErrorCode());
			//UNIQUE CONSTRAINT ERROR CODE 1
			if (e.getSQLException().getErrorCode() == 1) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Valor a modificar ya existe",e.getMessage()));
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
			FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error ",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
	}
	
	/**
	 * METODO PARA CAMBIAR DE FORMULARIO
	 */
	public void changeForm(){
		System.out.println("Cambiando Formulario " + this.mostrar);
		if (this.mostrar == true){
			this.mostrar = false;
		}
		else {
			this.mostrar = true;
		}
		System.out.println("Cambiando Formulario 1" + this.mostrar);
	}
	
	
	
}