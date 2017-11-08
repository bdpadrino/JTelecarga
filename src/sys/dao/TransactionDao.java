package sys.dao;

import java.util.List;
import sys.model.Transaction;

public interface TransactionDao {
	
	public List<Transaction> listTransactions();
	public Integer addTransaction(Transaction e);
	public void modifyTransaction(Transaction e);
	public void deleteTransaction(Integer id);
	public Transaction findByID(Integer id);
	
}
