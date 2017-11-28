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
    }
	
	public BitacoraError getBitacoraError() {
		return bitacoraError;
	}
	
	public void setBitacoraError(BitacoraError BitacoraError) {
		this.bitacoraError = BitacoraError;
	}

	public List<BitacoraError> getListBitacoraErrors() {
		listBitacoraErrors = cbe.listBitacoraErrors();
		return listBitacoraErrors;
	}
	
	public void setListBitacoraErrors(List<BitacoraError> listBitacoraErrors) {
		this.listBitacoraErrors = listBitacoraErrors;
	} 
	
	public void eliminarBitacoraError() {
		if (bitacoraError == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			cbe.deleteBitacoraError(bitacoraError.getId());
		}		
	}
	

}