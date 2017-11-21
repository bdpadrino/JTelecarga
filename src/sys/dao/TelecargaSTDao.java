package sys.dao;

import java.util.List;

import sys.model.TelecargaACT;
import sys.model.TelecargaST;

public interface TelecargaSTDao {
	
	public List<TelecargaST> listTelecargas();
	public Integer addTelecarga(TelecargaST e);
	public Integer addTelecargaACT_ST(TelecargaACT e);
	public void modifyTelecarga(TelecargaST e);
	public void deleteTelecarga(Integer id);
	public TelecargaST findByID(Integer id);
	
}
