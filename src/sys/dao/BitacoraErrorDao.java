package sys.dao;

import java.util.List;
import sys.model.BitacoraError;

public interface BitacoraErrorDao {
	
	public List<BitacoraError> listBitacoraErrors();
	public Integer addBitacoraError(BitacoraError e);
	public void modifyBitacoraError(BitacoraError e);
	public void deleteBitacoraError(Integer id);
	public BitacoraError findByID(Integer id);
	
}
