package sys.dao;

import java.util.List;
import sys.model.Mail;

public interface MailDao {		
	public List<Mail> listMails();
	public Integer addMail(Mail e);
	public void modifyMail(Mail e);
	public void deleteMail(Integer id);	
	public Mail findByID(Integer id);
}
