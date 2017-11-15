package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sys.dao.BduDao;
import sys.dao.TelecargaDao;
import sys.dao.imp.BduDaoImp;
import sys.dao.imp.TelecargaDaoImp;
import sys.model.Bdu;
import sys.model.Telecarga;

import java.io.Serializable;
import java.util.List;


@ManagedBean(name="telecargaBean")
@SessionScoped
public class TelecargaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	TelecargaDao ct = new TelecargaDaoImp();   
	BduDao cb = new BduDaoImp();
	
	private Telecarga Telecarga;  
	private Bdu bdu; 
	private List<Telecarga> listTelecargas;
	private List<Bdu> listBdus;
	FacesContext context;
	
	public TelecargaBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.Telecarga = new Telecarga();
		this.bdu = new Bdu();
    }
	
	public Telecarga getTelecarga() {
		return Telecarga;
	}
	
	public void setTelecarga(Telecarga Telecarga) {
		this.Telecarga = Telecarga;
	}
	
	public Bdu getBdu() {
		return bdu;
	}

	public void setBdu(Bdu bdu) {
		this.bdu = bdu;
	}
	
	public List<Telecarga> getListTelecargas() {
		System.out.println("buscando telecargas");
		listTelecargas = ct.listTelecargas();
		return listTelecargas;
	}
	
	public void setListTelecargas(List<Telecarga> listTelecargas) {
		this.listTelecargas = listTelecargas;
	} 
	
	public List<Bdu> getlistBdus() {
		System.out.println("buscando listBdus");
		listBdus = cb.listBdus();		
		return listBdus;
	}
	
	public void setListBdus(List<Bdu> listBdus) {
		this.listBdus = listBdus;
	} 
	
	
	public void eliminarTelecarga() {
		System.out.println("Enttrando a eliminar " +Telecarga.getRqtKey());
		ct.deleteTelecarga(Telecarga.getRqtKey());
	}
	
	public void getParamtersFromMaster() {
		try {
			//System.out.println("Obteniendo parametos");
			//Integer indice = new Integer( (String) context.getExternalContext().getRequestParameterMap().get("indice"));
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		}
}