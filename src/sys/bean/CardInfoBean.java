package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import sys.dao.CardInfoDao;
import sys.dao.imp.CardInfoDaoImp;
import sys.model.CardInfo;

import java.io.Serializable;
import java.util.List;


@ManagedBean(name="cardInfoBean")
@ViewScoped
public class CardInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	CardInfoDao ct = new CardInfoDaoImp();    
	
	private CardInfo cardInfo;  
	private CardInfo cardInfoToAdd; 
	private CardInfo selectedCardInfo; 
	private List<CardInfo> listCardInfos;
		
	public CardInfoBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.cardInfo = new CardInfo();
		this.cardInfoToAdd = new CardInfo();
		listCardInfos = ct.listCardInfos();	
    }
	
	public CardInfo getCardInfo() {
		return cardInfo;
	}
	
	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
	
	public CardInfo getCardInfoToAdd() {
		return cardInfoToAdd;
	}

	public void setCardInfoToAdd(CardInfo cardInfoToAdd) {
		this.cardInfoToAdd = cardInfoToAdd;
	}
	
	public List<CardInfo> getListCardInfos() {
		return listCardInfos;
	}
	
	public void setListCardInfos(List<CardInfo> listCardInfos) {
		this.listCardInfos = listCardInfos;
	} 
	
	public CardInfo getSelectedCardInfo() {
		return selectedCardInfo;
	}

	public void setSelectedCardInfo(CardInfo selectedCardInfo) {
		this.selectedCardInfo = selectedCardInfo;
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			CardInfo u = ((CardInfo) event.getObject()); 
			ct.modifyCardInfo(u);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificado Exitosamente",""));
		}
		catch(JDBCException e) {
			System.out.println("eror code "+e.getSQLException().getSQLState());
			if (e.getSQLException().getSQLState().equals("23000")) {
				FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_WARN,"Valor a modificar ya existe",e.getMessage()));
			}
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("addPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al Modificar",e.getMessage()));
			System.out.println("Mensaje "+e.getMessage());
			System.out.println("Causa "+e.getCause());
		}
    }
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((CardInfo) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         System.out.println("soi pasa por aca");
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
    /**
     * METODO PARA AGREGAR INFORMACION DE UN TARJETAHABIENTE
     */
	public void addCardInfo() {
		try {
			int id = ct.addCardInfo(cardInfoToAdd);
			cardInfoToAdd = new CardInfo();
			this.listCardInfos.add(cardInfoToAdd);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Guardada con Exito"," "));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Agregar",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}		
	}

	/**
	 * METODO PARA ELIMINAR UN TARJETAHABIENTE
	 * @param cardInfoReceived
	 */
	public void deleteCardInfo(CardInfo cardInfoReceived) { 
		try {
			if (cardInfoReceived == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= cardInfoReceived.getId();
				ct.deleteCardInfo(cardInfoReceived.getId());
				this.listCardInfos.remove(cardInfoReceived);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Tarjetahabiente "+ id+" Eliminado con exito",""));
			}	
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar Existen transacciones para este Tarjetahabiente",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}	
		
	}
	
	
	
	
}