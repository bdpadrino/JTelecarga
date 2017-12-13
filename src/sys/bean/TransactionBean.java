package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


import sys.dao.TransactionDao;
import sys.dao.imp.TransactionDaoImp;
import sys.model.Transaction;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="transactionBean")
//@ViewScoped
//@RequestScoped
@SessionScoped
public class TransactionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	TransactionDao ct = new TransactionDaoImp();    
	
	private Transaction transaction;  
	private Transaction transactionToAdd; 
	private Transaction selectedTransaction; 
	private List<Transaction> listTransactions;
	
		
	public TransactionBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.transaction = new Transaction();
		this.transactionToAdd = new Transaction();
		listTransactions = ct.listTransactions();	
    }
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Transaction getTransactionToAdd() {
		return transactionToAdd;
	}

	public void setTransactionToAdd(Transaction transactionToAdd) {
		this.transactionToAdd = transactionToAdd;
	}
	
	public List<Transaction> getListTransactions() {
		listTransactions = ct.listTransactions();	
		return listTransactions;
	}
	
	public void setListTransactions(List<Transaction> listTransactions) {
		this.listTransactions = listTransactions;
	} 
	
	public Transaction getSelectedTransaction() {
		return selectedTransaction;
	}

	public void setSelectedTransaction(Transaction selectedTransaction) {
		this.selectedTransaction = selectedTransaction;
	}

	public void addTransaction() {
		try {
			System.out.println(" fecha transa recibida"+transactionToAdd.getDate_transaction());
			
			int id = ct.addTransaction(transactionToAdd);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Guardada con Exito"," "));
			transactionToAdd = new Transaction();
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Agregar",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}		
	}
	
	public void modifyTransaction() {
		if (transaction != null) {
			int id = transaction.getSystems_trace_number();
			ct.modifyTransaction(transaction);
			FacesContext.getCurrentInstance().addMessage("messagesModify", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Modificada con Exito"," "));
		}
		else {
			FacesContext.getCurrentInstance().addMessage("messagesModify", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion nula"," "));
		}
	}

	public void deleteTransaction() { 
		try {
			if (transaction == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				System.out.println("entrando a  eliminar transaccion "+transaction.toString());
				int id= transaction.getSystems_trace_number();
				ct.deleteTransaction(transaction.getSystems_trace_number());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transaccion "+id+" Eliminada con exito",""));
			}	
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error al Agregar",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}	
		
	}
	
	public void deleteTransaction(Transaction transactionReceived) { 
		try {
			if (transactionReceived == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Debe Seleccionar una fila",""));
			}
			else {
				System.out.println("entrando a  eliminar transaccion "+transactionReceived.toString());
				int id= transactionReceived.getSystems_trace_number();
				ct.deleteTransaction(transactionReceived.getSystems_trace_number());
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