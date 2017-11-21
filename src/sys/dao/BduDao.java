package sys.dao;

import java.util.List;
import sys.model.Bdu;

public interface BduDao {
	
	public List<Bdu> listBdus();
	public Integer addBdu(Bdu e);
	public void modifyBdu(Bdu e);
	public void deleteBdu(Integer id);
	public Bdu findByID(Integer id);
	public Bdu findBduByFolio(String folio);
	
}
