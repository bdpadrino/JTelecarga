package sys.bean;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import sys.model.User;

@Named
@ViewScoped
public class PlantillaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4999989830563680789L;

	public void verificarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			User user = (User) context.getExternalContext().getSessionMap().get("usuario");
			if (user == null) {
				context.getExternalContext().redirect("index.xhtml");
			}
		}catch(Exception e){
			
		}
	}
}
