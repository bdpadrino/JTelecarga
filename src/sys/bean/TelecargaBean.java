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
	
	
	
	/**
	 * METODO PARA ELIMINAR UNA TRANSACCION POR EL ID
	 * @param bitacoraErrorReceived
	 */
	public void deleteTelecarga(TelecargaST telecarga){
		try {
			if (telecarga == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= telecarga.getId();
				ct.deleteTelecarga(id);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion "+id+" Eliminada con exito",""));
				refresh();
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa "   +e.getCause());
		}	
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
	
	
	public void setParameters() {
		try {
			System.out.println("recibida" +telecargaST.toString());
			HttpServletRequest mirequest= (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			mirequest.getAttribute("claveTelecarga") ;
			
		}catch(Exception e)
		{
			System.out.println("Error Mensaje "+e.getMessage());
			System.out.println("Error Causa "+e.getCause());
		}
	}
	
	
	public void refresh() {
		this.listTelecargas = ct.listTelecargas();
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