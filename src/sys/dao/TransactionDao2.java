package sys.dao;

import java.util.List;
import sys.model.Transaction2;

public interface TransactionDao2 {
	
	public List<Transaction2> listTransactions();
	public Integer addTransaction(Transaction2 e);
	public void modifyTransaction(Transaction2 e);
	public void deleteTransaction(Integer id);
	public Transaction2 findByID(Integer id);
	
}
