package sys.dao;

import java.sql.Date;
import java.util.List;
import sys.model.TelecargaST;

public interface TelecargaSTDao {
	
	public List<TelecargaST> listTelecargas();
	public List<TelecargaST> listTelecargasByDates(Date a, Date b);
	public Integer addTelecarga(TelecargaST e);
	public void modifyTelecarga(TelecargaST e);
	public void deleteTelecarga(Integer id);
	public TelecargaST findByID(Integer id);
	public TelecargaST findTelecargaByFolio (Integer folio);
	public TelecargaST findTelecargaByTerminal (String terminal);
	
}
