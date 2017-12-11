package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import sys.dao.TransactionDao;
import sys.dao.imp.TransactionDaoImp;
import sys.model.Transaction;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name="transactionBean")
@SessionScoped
public class TransactionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	TransactionDao ct = new TransactionDaoImp();    
	
	private Transaction transaction;  
	private Transaction transactionToAdd; 
	private List<Transaction> listTransactions;
	
		
	public TransactionBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.transaction = new Transaction();
		this.transactionToAdd = new Transaction();
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
	
	public void deleteTransaction() {
		if (transaction == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe Seleccionar una fila"));
		}
		else {
			ct.deleteTransaction(transaction.getSystems_trace_number());
		}	
		
	}
	
	public void addTransaction() {
		try {
			
			int id = ct.addTransaction(transactionToAdd);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Guardada con Exito"," "));
			RequestContext.getCurrentInstance().reset("addForm:addPanel");
			transactionToAdd = new Transaction();
			/*transaction.setCurrency(null);
			transaction.setAmount_transaction(null);
			transaction.setCard_acceptor_name(null);
			transaction.setCard_acceptor_terminal_id(null);
			transaction.setCard_holder(null);
			transaction.setCurrency(null);
			transaction.setPan("");*/
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
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


	
	
	
}