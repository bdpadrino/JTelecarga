package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sys.dao.BduDao;
import sys.dao.TelecargaSTDao;
import sys.dao.imp.BduDaoImp;
import sys.dao.imp.TelecargaSTDaoImp;
import sys.model.Bdu;
import sys.model.TelecargaST;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="telecargaBean")
@SessionScoped
public class TelecargaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	TelecargaSTDao ct = new TelecargaSTDaoImp();   
	BduDao cb = new BduDaoImp();
	
	private TelecargaST telecargaST;  
	private Bdu bdu; 
	private List<TelecargaST> listTelecargas;
	private List<Bdu> listBdus;
	FacesContext context;
	
	public TelecargaBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.telecargaST = new TelecargaST();
		this.bdu = new Bdu();
    }
	
	public TelecargaST getTelecarga() {
		return telecargaST;
	}
	
	public void setTelecarga(TelecargaST telecargaST) {
		this.telecargaST = telecargaST;
	}
	
	public Bdu getBdu() {
		return bdu;
	}

	public void setBdu(Bdu bdu) {
		this.bdu = bdu;
	}
	
	public List<TelecargaST> getListTelecargas() {
		System.out.println("buscando telecargas");
		listTelecargas = ct.listTelecargas();
		return listTelecargas;
	}
	
	public void setListTelecargas(List<TelecargaST> listTelecargas) {
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
		if (telecargaST == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			ct.deleteTelecarga(telecargaST.getRqtKey());
		}	
		
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