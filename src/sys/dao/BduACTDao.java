package sys.dao;

import java.util.List;
import sys.model.BduACT;

public interface BduACTDao {
	
	public List<BduACT> listBduACTs();
	public Integer addBduACT(BduACT e);
	public void modifyBduACT(BduACT e);
	public void deleteBduACT(Integer id);
	public BduACT findByID(Integer id);
	public BduACT findBduACTByFolioInACT(Integer folio);
	
}
