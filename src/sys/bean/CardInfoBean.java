package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


import sys.dao.CardInfoDao;
import sys.dao.imp.CardInfoDaoImp;
import sys.model.CardInfo;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="cardInfoBean")
//@ViewScoped
//@RequestScoped
@SessionScoped
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

	public void addCardInfo() {
		try {
			
			int id = ct.addCardInfo(cardInfoToAdd);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Guardada con Exito"," "));
			cardInfoToAdd = new CardInfo();
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Agregar",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}		
	}
	
	public void modifyCardInfo() {
		if (cardInfo != null) {
			int id = cardInfo.getId();
			ct.modifyCardInfo(cardInfo);
			FacesContext.getCurrentInstance().addMessage("messagesModify", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Modificada con Exito"," "));
		}
		else {
			FacesContext.getCurrentInstance().addMessage("messagesModify", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion nula"," "));
		}
	}

	public void deleteCardInfo(CardInfo cardInfoReceived) { 
		try {
			if (cardInfoReceived == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				System.out.println("entrando a  eliminar transaccion "+cardInfoReceived.toString());
				int id= cardInfoReceived.getId();
				ct.deleteCardInfo(cardInfoReceived.getId());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion "+id+" Eliminada con exito",""));
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Agregar",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}	
		
	}
	
	
	
}