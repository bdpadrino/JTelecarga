package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sys.dao.TelecargaSTDao;
import sys.dao.imp.TelecargaSTDaoImp;
import sys.model.TelecargaST;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="telecargaBean")
@SessionScoped
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
				this.listTelecargas.remove(telecarga);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Telecarga "+id+" Eliminada con exito",""));
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa "   +e.getCause());
		}	
	}
	
	
}