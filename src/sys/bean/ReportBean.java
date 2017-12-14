package sys.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import sys.model.Report;
import sys.util.CustomException;
import sys.util.HibernateUtilST;
import sys.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;




@ManagedBean(name="reportBean")
@RequestScoped
public class ReportBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Report report;
	private String search;
	private String searchBy;
	public ReportBean() {
		
	}
    
	@PostConstruct
    public void init() {
		this.setReport(new Report());
    }
		
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	public void downloadReport() {
		try 
        {   
			System.out.println("hola reporte abrir "+report.getReportName());
			Connection con = HibernateUtilST.getConnectionHibernate();
            Util util = new Util();

            Map<String,Object> parameter= new HashMap<String,Object>();
            parameter.put("reportName", report.getReportName());
            parameter.put("fechaInicio", util.addDateFormat(report.getStartDate()));
            parameter.put("fechaFin", util.addDateFormat(report.getEndDate()));

            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/"+report.getReportName()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parameter, con);
            
    		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    		response.addHeader("Content-disposition","attachment; filename="+report.getReportName().replace(".jasper","")+".pdf");
    		ServletOutputStream stream = response.getOutputStream();
    		
    		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    		
    		stream.flush();
    		stream.close();

    		FacesContext.getCurrentInstance().responseComplete();
            		
        }   
		catch(FileNotFoundException e) {
			System.out.println("Error FileNotFound" +e.getMessage());
			System.out.println("Causa FileNotFound" +e.getCause());
		}
        catch (Exception e) {
            System.out.println("Error Exception " +e.getMessage());
            System.out.println("Causa Exception" +e.getCause());
        }
				
	}
	
	public void generateReportPDF(ActionEvent actionEvent) throws CustomException{
		try {
			Connection con = HibernateUtilST.getConnectionHibernate();
	        Util util = new Util();
	        
	        System.out.println("fecha i recibida "+report.getStartDate());
	        System.out.println("fecha f recibida "+report.getEndDate());
	        System.out.println("fecha f recibida "+util.addDateFormat(util.sumarDiasFecha(report.getEndDate(), 1)));
	        
	        Map<String,Object> parameter= new HashMap<String,Object>();
            parameter.put("reportName", report.getReportName());
            parameter.put("fechaInicio", util.addDateFormat(util.sumarDiasFecha(report.getStartDate(), 1)));
            parameter.put("fechaFin", util.addDateFormat(util.sumarDiasFecha(report.getEndDate(), 1)));
            
            if (search != null) {
            	System.out.println("llenando la busuqeda"+search);
            	 parameter.put("search", search);
            }
			
			File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/"+report.getReportName()));		
			
			byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parameter, con);
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream outStream = response.getOutputStream();
			outStream.write(bytes, 0 , bytes.length);
			outStream.flush();
			outStream.close();

			FacesContext.getCurrentInstance().responseComplete();

		}
		catch(FileNotFoundException e){
			//throw new CustomException ("Archivo No Encontrado",e);
		 System.out.println("archivo no encontrado" +e.getMessage());
		}
		catch(Exception e){
			 System.out.println("Error" +e.getMessage());
			 System.out.println("Error" +e.getCause());
		}
		
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	

}