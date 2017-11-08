package sys.dao;

import java.util.List;
import sys.model.Telecarga;

public interface TelecargaDao {
	
	public List<Telecarga> listTelecargas();
	public Integer addTelecarga(Telecarga e);
	public void modifyTelecarga(Telecarga e);
	public void deleteTelecarga(Integer id);
	public Telecarga findByID(Integer id);
	
}
