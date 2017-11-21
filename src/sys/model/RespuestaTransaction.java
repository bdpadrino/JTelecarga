package sys.model;

public class RespuestaTransaction {

	private String status;
	private String message;
	private String iso;
	private TelecargaST telecarga;
	
	public RespuestaTransaction() {
	
	}
	
	public RespuestaTransaction(String status, String message, String iso) {
		super();
		this.status = status;
		this.message = message;
		this.iso = iso;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}

	public TelecargaST getTelecarga() {
		return telecarga;
	}

	public void setTelecarga(TelecargaST telecarga) {
		this.telecarga = telecarga;
	}
	
	
}
