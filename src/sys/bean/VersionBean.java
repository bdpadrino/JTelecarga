package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import sys.dao.VersionDao;
import sys.dao.imp.VersionDaoImp;
import sys.model.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name="versionBean")
@ViewScoped
public class VersionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	VersionDao ct = new VersionDaoImp();    
	
	private Version version;  
	private Version versionToAdd; 
	private List<Version> listVersions;
	private ArrayList<Version> listVersionsArray;
	private String prueba;
	
		
	public VersionBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.version = new Version();
		this.listVersions = ct.listVersions();	
		this.prueba = "SIN TOCAR";
		
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
	
	/**
	 * METODO USADO PARA MODIFICAR UNA TRANSACCION SELECCIONADA EN DATATABLE
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		Version version = ((Version) event.getObject()); 
		modifyVersion(version);
    }
    
	/**
	 * METODO USADO PARA CANCELAR LA EDICION DE UNA FILA EN UN DATATABLE
	 * @param event
	 */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((Version) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
	/**
	 * METODO PARA AGREGAR VERSIONES
	 */
	public void addVersion() {
		try {
			int id = ct.addVersion(version);
			this.listVersions.add(version);
			this.version = new Version();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Versión Id "+id+" Guardada con Exito"," "));
		} 
		catch(JDBCException e) {
			System.out.println("Sql State "+e.getSQLException().getSQLState());
			System.out.println("Eror code "+e.getSQLException().getErrorCode());
			//UNIQUE CONSTRAINT ERROR CODE 1
			if (e.getSQLException().getErrorCode() == 1) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Valor a modificar ya existe",e.getMessage()));
			}
			//MANDATORIEDAD ERROR CODE 1400
			if (e.getSQLException().getErrorCode() == 1400) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Campo que no puede ser nulo",e.getMessage()));
			}

		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa   " +e.getCause());
		}		
	}
	
	
	/**
	 * METODO PARA MODIFICAR VERSIONES
	 */
	public void modifyVersion(Version version) {
		try {
			int id = version.getId();
			ct.modifyVersion(version);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Modificado Exitosamente",""));
		}
		catch(JDBCException e) {
			System.out.println("eror code "+e.getSQLException().getSQLState());
			if (e.getSQLException().getSQLState().equals("23000")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Valor a modificar ya existe",e.getMessage()));
			}
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al Modificar ",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
	}

	/**
	 * METODO PARA ELIMINAR VERSIONES 
	 */
	public void deleteVersion(Version versionReceived) {
		try {
			if (versionReceived == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= versionReceived.getId();
				ct.deleteVersion(id);
				this.listVersions.remove(versionReceived);
				prueba = "Tocado";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion "+id+" Eliminada con exito",""));
				//refresh();
				
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa "   +e.getCause());
		}	
		
	}
	
	/**
	 * METODO USADO PARA ACTUALIZAR LA LISTA CON LA INFORMACION 
	 */
	public void refresh() {
		//this.listVersions = ct.listVersions();
		
	}

	public String getPrueba() {
		return prueba;
	}

	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}
	
	
	
}