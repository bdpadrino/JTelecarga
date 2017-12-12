package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//import org.primefaces.context.RequestContext;

import sys.dao.VersionDao;
import sys.dao.imp.VersionDaoImp;
import sys.model.Version;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="versionBean")
@SessionScoped
public class VersionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	VersionDao ct = new VersionDaoImp();    
	
	private Version version;  
	private Version versionToAdd; 
	private List<Version> listVersions;
	
		
	public VersionBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.version = new Version();
		this.listVersions = ct.listVersions();	
    }
	
	public Version getVersion() {
		return version;
	}
	
	public void setVersion(Version version) {
		this.version = version;
	}
	
	public List<Version> getListVersions() {
			
		return listVersions;
	}
	
	public void setListVersions(List<Version> listVersions) {
		this.listVersions = listVersions;
	} 
	
	public Version getVersionToAdd() {
		return versionToAdd;
	}

	public void setVersionToAdd(Version versionToAdd) {
		this.versionToAdd = versionToAdd;
	}
	
	public void addVersion() {
		try {
			int id = ct.addVersion(version);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Versión Id "+id+" Guardada con Exito"," "));
			version = new Version();
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}		
	}
	
	public void modifyVersion() {
		if (version != null) {
			int id = version.getId();
			ct.modifyVersion(version);
			FacesContext.getCurrentInstance().addMessage("messagesModify", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Modificada con Exito"," "));
			
		}
		else {
			FacesContext.getCurrentInstance().addMessage("messagesModify", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion nula"," "));
		}
	}

	public void deleteVersion() {
		if (version == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			ct.deleteVersion(version.getId());
		}	
		
	}
	
	
	
	
	
}