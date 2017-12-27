package sys.dao;

import java.util.List;

import sys.model.TerminalVersion;


public interface TerminalVersionDao {
	
	public List<TerminalVersion> listTerminalVersions();
	public Integer addTerminalVersion(TerminalVersion e);
	public void modifyTerminalVersion(TerminalVersion e);
	public void deleteTerminalVersion(Integer id);
	public TerminalVersion findByID(Integer id);
	Double findLastVersion (String modelo, String tipoApp);
	
}
