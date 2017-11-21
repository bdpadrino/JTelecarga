package sys.dao;

import java.util.List;

import sys.model.TerminalInfo;


public interface TerminalInfoDao {
	
	public List<TerminalInfo> listTerminalInfos();
	public Integer addTerminalInfo(TerminalInfo e);
	public void modifyTerminalInfo(TerminalInfo e);
	public void deleteTerminalInfo(Integer id);
	public TerminalInfo findByID(Integer id);
	TerminalInfo findTerminalInfoByFolio (Integer folio);
	
}
