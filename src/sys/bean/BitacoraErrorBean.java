package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sys.dao.BitacoraErrorDao;
import sys.dao.imp.BitacoraErrorDaoImp;
import sys.model.BitacoraError;

import java.io.Serializable;
import java.util.List;


@ManagedBean(name="bitacoraErrorBean")
@SessionScoped
public class BitacoraErrorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	BitacoraErrorDao cbe = new BitacoraErrorDaoImp();   		
	private BitacoraError bitacoraError;  
	private List<BitacoraError> listBitacoraErrors;
	
	
	public BitacoraErrorBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.bitacoraError = new BitacoraError();
		this.listBitacoraErrors = cbe.listBitacoraErrors();
    }
	
	public BitacoraError getBitacoraError() {
		return bitacoraError;
	}
	
	public void setBitacoraError(BitacoraError BitacoraError) {
		this.bitacoraError = BitacoraError;
	}

	public List<BitacoraError> getListBitacoraErrors() {
		return listBitacoraErrors;
	}
	
	public void setListBitacoraErrors(List<BitacoraError> listBitacoraErrors) {
		this.listBitacoraErrors = listBitacoraErrors;
	} 
	
	
	/**
	 * METODO PARA ELIMINAR UNA FILA DE UNA SELECCION EN UN DATATABLE
	 */
	public void deleteBitacoraError() {
		try {
			if (bitacoraError == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= bitacoraError.getId();
				cbe.deleteBitacoraError(id);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion "+id+" Eliminada con exito",""));
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa "   +e.getCause());
		}		
	}
	
	/**
	 * METODO PARA ELIMINAR UNA FILA POR EL ID
	 * @param bitacoraErrorReceived
	 */
	public void deleteBitacoraError(BitacoraError bitacoraErrorReceived) { 
		try {
			if (bitacoraErrorReceived == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= bitacoraErrorReceived.getId();
				cbe.deleteBitacoraError(id);
				this.listBitacoraErrors.remove(bitacoraErrorReceived);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Bitacora "+id+" Eliminada con exito",""));
				
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa "   +e.getCause());
		}	
		
	}
	

}