package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;


import sys.dao.TransactionDao2;
import sys.dao.imp.TransactionDaoImp2;
import sys.model.Transaction2;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="transactionBean2")
//@ViewScoped
//@RequestScoped
@SessionScoped
public class TransactionBean2 implements Serializable {

	private static final long serialVersionUID = 1L;

	TransactionDao2 ct = new TransactionDaoImp2();    
	
	private Transaction2 transaction;  
	private Transaction2 transactionToAdd; 
	private Transaction2 selectedTransaction; 
	private List<Transaction2> listTransactions;
	
		
	public TransactionBean2() {
		
	}
    
	@PostConstruct
    public void init() {
		this.transaction = new Transaction2();
		this.transactionToAdd = new Transaction2();
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

	public void addTransaction() {
		try {
			System.out.println(" fecha transa recibida"+transactionToAdd.getDate_transaction());
			
			int id = ct.addTransaction(transactionToAdd);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Guardada con Exito"," "));
			transactionToAdd = new Transaction2();
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
	
	public void deleteTransaction(Transaction2 transactionReceived) { 
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