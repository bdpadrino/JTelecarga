package sys.dao;

import java.util.List;
import sys.model.Version;

public interface VersionDao {		
	public List<Version> listVersions();
	public Integer addVersion(Version e);
	public void modifyVersion(Version e);
	public void deleteVersion(Integer id);	
	public Version findByID(Integer id);
}
