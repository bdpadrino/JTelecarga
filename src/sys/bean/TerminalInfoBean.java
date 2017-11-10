package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//import sys.dao.TerminalInfoDao;
import sys.dao.imp.TerminalInfoDaoImp;
import sys.model.TerminalInfo;

import java.io.Serializable;
import java.util.List;


@ManagedBean(name="terminalInfoBean")
@SessionScoped
public class TerminalInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	//TerminalInfoDao
	TerminalInfoDaoImp cti = new TerminalInfoDaoImp();    
	
	private TerminalInfo terminalInfo;  
	private List<TerminalInfo> listTerminalInfos;
	
		
	public TerminalInfoBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.terminalInfo = new TerminalInfo();
    }
	
	public TerminalInfo getTerminalInfo() {
		return terminalInfo;
	}
	
	public void setTerminalInfo(TerminalInfo terminalInfo) {
		this.terminalInfo = terminalInfo;
	}
	
	public List<TerminalInfo> getListTerminalInfos() {
		System.out.println("buscando TerminalInfos");
		listTerminalInfos = cti.listTerminalInfos();		
		return listTerminalInfos;
	}
	
	public void setListTerminalInfos(List<TerminalInfo> listTerminalInfos) {
		this.listTerminalInfos = listTerminalInfos;
	} 
	
	public void eliminarTerminalInfo() {
		cti.deleteTerminalInfo(terminalInfo.getId());
	}
	
}