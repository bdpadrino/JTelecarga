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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import sys.util.*;

@ManagedBean(name="transactionBean")
@SessionScoped
public class TransactionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	TransactionDao ct = new TransactionDaoImp();    
	
	private Transaction transaction;  
	private List<Transaction> listTransactions;
	
		
	public TransactionBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.transaction = new Transaction();
    }
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public List<Transaction> getListTransactions() {
		listTransactions = ct.listTransactions();		
		return listTransactions;
	}
	
	public void setListTransactions(List<Transaction> listTransactions) {
		this.listTransactions = listTransactions;
	} 
	
	public void deleteTransaction() {
		ct.deleteTransaction(transaction.getSystems_trace_number());
	}
	
	public void addTransaction() {
		try {
			int id = ct.addTransaction(transaction);
			FacesContext.getCurrentInstance().addMessage("messages2", new FacesMessage(FacesMessage.SEVERITY_INFO,"Transacción número "+id+" Guardada con Exito"," "));
		} 
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
			System.out.println("mensaje exc" +e.getMessage());
			System.out.println("causa" +e.getCause());
		}		
	}
	
	public void modifyTransaction() {
		
	}
	
	
	
}