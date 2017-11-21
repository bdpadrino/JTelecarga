package sys.dao;

import java.util.List;
import sys.model.TelecargaACT;

public interface TelecargaACTDao {
	
	public List<TelecargaACT> listTelecargas();
	public Integer addTelecarga(TelecargaACT e);
	
	public void modifyTelecarga(TelecargaACT e);
	public void deleteTelecarga(Integer id);
	public TelecargaACT findByID(Integer id);
	
}
