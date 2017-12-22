package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sys.dao.SolicitudTelecargaDao;
import sys.dao.imp.SolicitudTelecargaDaoImp;

import sys.model.SolicitudTelecarga;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="solicitudTelecargaBean")
@ViewScoped
public class SolicitudTelecargaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8391406716939255167L;
	  	
	private SolicitudTelecarga solicitudTelecarga;  
	SolicitudTelecargaDao cst = new SolicitudTelecargaDaoImp();  
	private List<SolicitudTelecarga> listSolicitudTelecargas;
	
	public SolicitudTelecargaBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.solicitudTelecarga = new SolicitudTelecarga();
		this.listSolicitudTelecargas = cst.listSolicitudTelecargas();
    }
	
	public SolicitudTelecarga getSolicitudTelecarga() {
		return solicitudTelecarga;
	}
	
	public void setSolicitudTelecarga(SolicitudTelecarga solicitudTelecarga) {
		this.solicitudTelecarga = solicitudTelecarga;
	}
	
	public List<SolicitudTelecarga> getListSolicitudTelecargas() {
		return listSolicitudTelecargas;
	}
	
	public void setListSolicitudTelecargas(List<SolicitudTelecarga> listSolicitudTelecargas) {
		this.listSolicitudTelecargas = listSolicitudTelecargas;
	} 
	
	/**
	 * METODO PARA ELIMINAR UNA FILA DE UNA SELECCION EN UN DATATABLE
	 */
	public void deleteDownloadRequest() {
		try {
			if (solicitudTelecarga == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
			}
			else {
				int id = solicitudTelecarga.getId();
				cst.deleteSolicitudTelecarga(id);			
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
	
	
	/**
	 * METODO PARA ELIMINAR UNA TRANSACCION POR EL ID
	 * @param bitacoraErrorReceived
	 */
	public void deleteDownloadRequest(SolicitudTelecarga DownloadRequestReceived){
		try {
			if (DownloadRequestReceived == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= DownloadRequestReceived.getId();
				cst.deleteSolicitudTelecarga(id);
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
	
	/** 
	 * METODO USADO PARA RECARGAR LA LISTA DE SOLICITUDES DE TELECARGA
	 */
	public void refresh() {
		this.listSolicitudTelecargas = cst.listSolicitudTelecargas();
	}
	
	
	
}