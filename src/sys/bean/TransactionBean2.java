package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.JDBCException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import sys.dao.TransactionDao2;
import sys.dao.imp.TransactionDaoImp2;
import sys.model.CardInfo;
import sys.model.Transaction2;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="transactionBean2")
@ViewScoped
public class TransactionBean2 implements Serializable {

	private static final long serialVersionUID = 1L;

	TransactionDao2 ct = new TransactionDaoImp2();    
	
	private Transaction2 transaction;  
	private Transaction2 transactionToAdd; 
	private Transaction2 selectedTransaction; 
	private List<Transaction2> listTransactions;
	private CardInfo cardInfo;
	
		
	public TransactionBean2() {
		
	}
    
	@PostConstruct
    public void init() {
		this.transaction = new Transaction2();
		this.transactionToAdd = new Transaction2();
		this.cardInfo = new CardInfo();
		listTransactions = ct.listTransactions();	
    }
	
	public Transaction2 getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Transaction2 transaction) {
		this.transaction = transaction;
	}
	
	public Transaction2 getTransactionToAdd() {
		return transactionToAdd;
	}

	public void setTransactionToAdd(Transaction2 transactionToAdd) {
		this.transactionToAdd = transactionToAdd;
	}
	
	public List<Transaction2> getListTransactions() {
		return listTransactions;
	}
	
	public void setListTransactions(List<Transaction2> listTransactions) {
		this.listTransactions = listTransactions;
	} 
	
	public Transaction2 getSelectedTransaction() {
		return selectedTransaction;
	}

	public void setSelectedTransaction(Transaction2 selectedTransaction) {
		this.selectedTransaction = selectedTransaction;
	}

	public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
	
	/**
	 * METODO USADO PARA MODIFICAR UNA TRANSACCION SELECCIONADA EN DATATABLE
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		Transaction2 transaction = ((Transaction2) event.getObject()); 
		modifyTransaction(transaction);
    }
	
	/**
	 * METODO USADO PARA CANCELAR LA EDICION DE UNA FILA EN UN DATATABLE
	 * @param event
	 */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+((Transaction2) event.getObject()).getSystems_trace_number());
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
	 * METODO PARA AGREGAR TRANSACCIONES
	 */
	public void addTransaction() {
		try {
			transactionToAdd.setCard_info(cardInfo);
			int id = ct.addTransaction(transactionToAdd);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Guardada con Exito"," "));
			transactionToAdd = new Transaction2();
			refresh();
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Agregar",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}		
	}
	
	/**
	 * METODO PARA MODIFICAR UNA TRANSACCION
	 * @param transactionReceived
	 */
	public void modifyTransaction(Transaction2 transactionReceived) { 
		try {
			int id =  transactionReceived.getSystems_trace_number();
			ct.modifyTransaction(transactionReceived);
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
	 * METODO PARA ELIMINAR UNA TRANSACCION POR EL ID
	 * @param transactionReceived
	 */
	public void deleteTransaction(Transaction2 transactionReceived) { 
		try {
			if (transactionReceived == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				int id= transactionReceived.getSystems_trace_number();
				ct.deleteTransaction(id);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion "+id+" Eliminada con exito",""));
				refresh();
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Eliminar",e.getMessage()));
			System.out.println("Mensaje " +e.getMessage());
			System.out.println("Causa "   +e.getCause());
		}	
		
	}
	
	/**
	 * METODO USADO PARA RECARGAR LA LISTA DE TRANSACCIONES
	 */
	public void refresh() {
		this.listTransactions = ct.listTransactions();
	}

	
	
	
}