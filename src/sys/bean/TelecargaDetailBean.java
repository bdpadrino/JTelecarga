package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import sys.dao.TelecargaSTDao;
import sys.dao.imp.TelecargaSTDaoImp;
import sys.model.TelecargaST;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="telecargaBean")
@ViewScoped
public class TelecargaDetailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TelecargaST telecargaST;  
	
	FacesContext context;
	
	public TelecargaDetailBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.telecargaST = new TelecargaST();

    }
	
	public TelecargaST getTelecargaST() {
		return telecargaST;
	}

	public void setTelecargaST(TelecargaST telecargaST) {
		this.telecargaST = telecargaST;
	}

	public void getParameters() {
		try {
			System.out.println("recibida" +telecargaST.toString());
		
			HttpServletRequest mirequest= (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			mirequest.setAttribute("claveTelecarga", telecargaST);
			
		}catch(Exception e)
		{
			System.out.println("Error Mensaje "+e.getMessage());
			System.out.println("Error Causa "+e.getCause());
		}
	}
	
}