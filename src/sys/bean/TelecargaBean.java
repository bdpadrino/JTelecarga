package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sys.dao.TelecargaDao;
import sys.dao.imp.TelecargaDaoImp;
import sys.model.Telecarga;

import java.io.Serializable;
import java.util.List;


@ManagedBean(name="telecargaBean")
@SessionScoped
public class TelecargaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	TelecargaDao ct = new TelecargaDaoImp();    
	
	private Telecarga Telecarga;  
	private List<Telecarga> listTelecargas;
	
		
	public TelecargaBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.Telecarga = new Telecarga();
    }
	
	public Telecarga getTelecarga() {
		return Telecarga;
	}
	
	public void setTelecarga(Telecarga Telecarga) {
		this.Telecarga = Telecarga;
	}
	
	public List<Telecarga> getListTelecargas() {
		listTelecargas = ct.listTelecargas();
		
		return listTelecargas;
	}
	
	public void setListTelecargas(List<Telecarga> listTelecargas) {
		this.listTelecargas = listTelecargas;
	} 
	
	
	
}