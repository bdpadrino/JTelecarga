package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sys.dao.SolicitudTelecargaDao;
import sys.dao.imp.SolicitudTelecargaDaoImp;
import sys.model.SolicitudTelecarga;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="solicitudTelecargaBean")
@SessionScoped
public class SolicitudTelecargaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8391406716939255167L;
	  	
	private SolicitudTelecarga solicitudTelecarga;  
	SolicitudTelecargaDao cti = new SolicitudTelecargaDaoImp();  
	private List<SolicitudTelecarga> listSolicitudTelecargas;
	
	public SolicitudTelecargaBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.solicitudTelecarga = new SolicitudTelecarga();
    }
	
	public SolicitudTelecarga getSolicitudTelecarga() {
		return solicitudTelecarga;
	}
	
	public void setSolicitudTelecarga(SolicitudTelecarga solicitudTelecarga) {
		this.solicitudTelecarga = solicitudTelecarga;
	}
	
	public List<SolicitudTelecarga> getListSolicitudTelecargas() {
		System.out.println("buscando SolicitudTelecargas");
		listSolicitudTelecargas = cti.listSolicitudTelecargas();		
		return listSolicitudTelecargas;
	}
	
	public void setListSolicitudTelecargas(List<SolicitudTelecarga> listSolicitudTelecargas) {
		this.listSolicitudTelecargas = listSolicitudTelecargas;
	} 
	
	public void eliminarSolicitudTelecarga() {
		if (solicitudTelecarga == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			cti.deleteSolicitudTelecarga(solicitudTelecarga.getId());
		}
		
	}
	
}