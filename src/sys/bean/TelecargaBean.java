package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import sys.dao.TelecargaSTDao;
import sys.dao.imp.TelecargaSTDaoImp;
import sys.model.Bdu;
import sys.model.TelecargaST;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="telecargaBean")
@SessionScoped
//@ViewScoped
//@RequestScoped
public class TelecargaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TelecargaST telecargaST;  
	private List<TelecargaST> listTelecargas;
	
	TelecargaSTDao ct = new TelecargaSTDaoImp();  

	FacesContext context;
	
	public TelecargaBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.telecargaST = new TelecargaST();
		this.listTelecargas = ct.listTelecargas();
    }
	
	public TelecargaST getTelecargaST() {
		return telecargaST;
	}

	public void setTelecargaST(TelecargaST telecargaST) {
		this.telecargaST = telecargaST;
	}

	public List<TelecargaST> getListTelecargas() {
		return listTelecargas;
	}
	
	public void setListTelecargas(List<TelecargaST> listTelecargas) {
		this.listTelecargas = listTelecargas;
	} 
	
	public void eliminarTelecarga() {
		if (telecargaST == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			ct.deleteTelecarga(telecargaST.getId());
		}	
		
	}
	
	public void getParamters() {
		try {
			System.out.println("recibida" +telecargaST.toString());
		
			HttpServletRequest mirequest= (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			mirequest.setAttribute("claveTelecarga", telecargaST);
			
			System.out.println("exploto");
		}catch(Exception e)
		{
			System.out.println("Error Mensaje "+e.getMessage());
			System.out.println("Error Causa "+e.getCause());
		}
	}
	
	/*
	public void getParamtersFromMaster() {
		try {
			//System.out.println("Obteniendo parametos");
			//Integer indice = new Integer( (String) context.getExternalContext().getRequestParameterMap().get("indice"));
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}*/
}