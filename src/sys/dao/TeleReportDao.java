package sys.dao;

import java.util.List;

import sys.model.TeleReport;

public interface TeleReportDao {
	
	public List<TeleReport> listTeleReports();
	public Integer addTeleReport(TeleReport e);	
	public void modifyTeleReport(TeleReport e);
	public void deleteTeleReport(Integer id);
	public TeleReport findByID(Integer id);
	
}
