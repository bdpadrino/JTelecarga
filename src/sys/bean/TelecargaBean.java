package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;

import sys.dao.TelecargaSTDao;
import sys.dao.imp.TelecargaSTDaoImp;
import sys.model.TelecargaST;
import sys.util.Util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@ManagedBean(name="telecargaBean")
@SessionScoped
public class TelecargaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TelecargaST telecargaST;  
	private List<TelecargaST> listTelecargas;
	private Date startDate, endDate;
	Util util = new Util();
	
	//PAGINACION
	private LazyDataModel<TelecargaST> listTelecargasLazyModel;
	
	TelecargaSTDao ct = new TelecargaSTDaoImp();  

	FacesContext context;
	
	public TelecargaBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.telecargaST = new TelecargaST();
		System.out.println("01/"+util.currentYearMonth()+ "fin" +util.daysInAMonth()+"/"+util.currentYearMonth());
		this.listTelecargas = ct.listTelecargasByDates(util.stringToSqlDate("01/"+util.currentYearMonth()),util.stringToSqlDate(util.daysInAMonth()+"/"+util.currentYearMonth()));
		//this.listTelecargas = ct.listTelecargas();
		
		//PAGINACION
		//this.listTelecargasLazyModel = new TelecargaSTLazyDataModel();
		/*final Long numEvents = (Long) dao.findByQuery("select count(id) from Footballer", -1, -1, null).get(0);
		public List<TelecargaST> fetchLazyData(int first, int pageSize) {
			return ct.findByQuery("from TelecargaST", first, pageSize, null);
		}*/
		//this.listTelecargasLazyModel = ct.listTelecargasPaginator();
    }
	
	public TelecargaST getTelecargaST() {
		return telecargaST;
	}

	public void setTelecargaST(TelecargaST telecargaST) {
		this.telecargaST = telecargaST;
	}

	public List<TelecargaST> getListTelecargas() {
		return listTelecargas;
	}
	
	public void setListTelecargas(List<TelecargaST> listTelecargas) {
		this.listTelecargas = listTelecargas;
	} 
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	//PAGINACION
	public LazyDataModel<TelecargaST> getListTelecargaslazyModel() {
		return listTelecargasLazyModel;
	}

	public void setListTelecargaslazyModel(LazyDataModel<TelecargaST> listTelecargaslazyModel) {
		this.listTelecargasLazyModel = listTelecargaslazyModel;
	}

	/**
	 * METODO PARA ELIMINAR UNA TRANSACCION POR EL ID
	 * @param telecarga
	 */
	public void deleteTelecarga(TelecargaST telecarga){
		try {
			if (telecarga == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= telecarga.getId();
				ct.deleteTelecarga(id);
				this.listTelecargas.remove(telecarga);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Telecarga "+id+" Eliminada con exito",""));
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa "   +e.getCause());
		}	
	}

	public void loadList(){
		System.out.println("Entro "+startDate +" "+ endDate);
	
		this.listTelecargas = ct.listTelecargasByDates(util.addDateFormat(util.sumarDiasFecha(startDate, 1)),util.addDateFormat(util.sumarDiasFecha(endDate, 1)));
		
	}



	
	
}