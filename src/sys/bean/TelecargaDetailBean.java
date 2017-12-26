package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import sys.model.TelecargaST;
import java.io.Serializable;


@ManagedBean(name="telecargaDetailBean")
@RequestScoped
public class TelecargaDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TelecargaST telecarga;  
	
	FacesContext context;
	
	public TelecargaDetailBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.telecarga = new TelecargaST();
    }
	
	public TelecargaST getTelecarga() {
		return telecarga;
	}

	public void setTelecarga(TelecargaST telecarga) {
		this.telecarga = telecarga;
	}

	public void getParameters() {
		try {
			System.out.println("recibida" +telecarga.toString());
		
			HttpServletRequest mirequest= (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			mirequest.setAttribute("claveTelecarga", telecarga);
			this.setTelecarga(telecarga);
			System.out.println("recibida 2" +telecarga.toString());
			
		}catch(Exception e)
		{
			System.out.println("Error Mensaje 	"+e.getMessage());
			System.out.println("Error Causa 	"+e.getCause());
		}
	}
	
}