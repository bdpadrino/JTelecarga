package sys.model;

import java.util.Date;

public class Report {

	private Date startDate;
	private Date endDate;
	private String reportName;
	private String reportPath;
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
	@Override
	public String toString() {
		return "Report [startDate=" + startDate + ", endDate=" + endDate + ", reportName=" + reportName
				+ ", reportPath=" + reportPath + "]";
	}
	
	
	
}
