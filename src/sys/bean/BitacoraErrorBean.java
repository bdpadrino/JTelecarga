package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sys.dao.BitacoraErrorDao;
import sys.dao.imp.BitacoraErrorDaoImp;
import sys.model.BitacoraError;
import sys.util.Util;

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
		System.out.println("buscando BitacoraErrors");
		listBitacoraErrors = cbe.listBitacoraErrors();
		return listBitacoraErrors;
	}
	
	public void setListBitacoraErrors(List<BitacoraError> listBitacoraErrors) {
		this.listBitacoraErrors = listBitacoraErrors;
	} 
	
	public void eliminarBitacoraError() {
		System.out.println("Enttrando a eliminar " +bitacoraError.getId());
		cbe.deleteBitacoraError(bitacoraError.getId());
	}
	
	public void generaArchivo() {
		System.out.println("ENTRO");
		Util util =new Util();
		util.writeFile("Err","error por x y z");
	}
	

}