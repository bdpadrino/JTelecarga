package sys.dao;

import java.util.List;

import sys.model.SolicitudTelecarga;


public interface SolicitudTelecargaDao {
	
	public List<SolicitudTelecarga> listSolicitudTelecargas();
	public Integer addSolicitudTelecarga(SolicitudTelecarga e);
	public void modifySolicitudTelecarga(SolicitudTelecarga e);
	public void deleteSolicitudTelecarga(Integer id);
	public SolicitudTelecarga findByID(Integer id);
	SolicitudTelecarga findSolicitudTelecargaByFolio (Integer folio);
	
}
