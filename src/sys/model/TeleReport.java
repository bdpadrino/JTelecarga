package sys.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TELE_REPORT")
public class TeleReport implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8891923786311280894L;


	@Id
	@Column(name = "id")
	private Integer id; 						
	@Column(name = "aplDescripción")
	private String  aplDescripción;		     
	@Column(name = "bcoNombre")
	private String  bcoNombre;				 
	@Column(name = "comAfiliación")
	private Integer comAfiliación; 			 
	@Column(name = "comNombre")
	private String  comNombre; 				 
	@Column(name = "comDomicilio")
	private String  comDomicilio;			 
    @Column(name = "telLocal")
    private String  telLocal;                
    @Column(name = "telAvantel")
    private String  telAvantel;	
    @Column(name = "telTelmex")
    private String  telTelmex;              
    @Column(name = "odtFolioTelecarga")
    private Integer odtFolioTelecarga; 		 
    @Column(name = "modModelo")
    private String  modModelo;               
    @Column(name = "cTram")
    private String  cTram;                   
    @Column(name = "terId")
    private String  terId;					
    @Column(name = "terIdEncr")
    private String  terIdEncr;               
    @Column(name = "proveedor")
    private String  proveedor;               
    @Column(name = "comPoblacion")
    private String  comPoblacion;            
    @Column(name = "numSerie")
    private String  numSerie;               
    @Column(name = "fechaTelecarga")
    private Date    fechaTelecarga;
	
    public TeleReport() {
		
	}
	
	public TeleReport(Integer id, String aplDescripción, String bcoNombre, Integer comAfiliación, String comNombre,
			String comDomicilio, String telLocal, String telAvantel, String telTelmex, Integer odtFolioTelecarga,
			String modModelo, String cTram, String terId, String terIdEncr, String proveedor, String comPoblacion,
			String numSerie, Date fechaTelecarga) {
		super();
		this.id = id;
		this.aplDescripción = aplDescripción;
		this.bcoNombre = bcoNombre;
		this.comAfiliación = comAfiliación;
		this.comNombre = comNombre;
		this.comDomicilio = comDomicilio;
		this.telLocal = telLocal;
		this.telAvantel = telAvantel;
		this.telTelmex = telTelmex;
		this.odtFolioTelecarga = odtFolioTelecarga;
		this.modModelo = modModelo;
		this.cTram = cTram;
		this.terId = terId;
		this.terIdEncr = terIdEncr;
		this.proveedor = proveedor;
		this.comPoblacion = comPoblacion;
		this.numSerie = numSerie;
		this.fechaTelecarga = fechaTelecarga;
	}   
	
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAplDescripción() {
		return aplDescripción;
	}
	public void setAplDescripción(String aplDescripción) {
		this.aplDescripción = aplDescripción;
	}
	public String getBcoNombre() {
		return bcoNombre;
	}
	public void setBcoNombre(String bcoNombre) {
		this.bcoNombre = bcoNombre;
	}
	public Integer getComAfiliación() {
		return comAfiliación;
	}
	public void setComAfiliación(Integer comAfiliación) {
		this.comAfiliación = comAfiliación;
	}
	public String getComNombre() {
		return comNombre;
	}
	public void setComNombre(String comNombre) {
		this.comNombre = comNombre;
	}
	public String getComDomicilio() {
		return comDomicilio;
	}
	public void setComDomicilio(String comDomicilio) {
		this.comDomicilio = comDomicilio;
	}
	public String getTelLocal() {
		return telLocal;
	}
	public void setTelLocal(String telLocal) {
		this.telLocal = telLocal;
	}
	public String getTelAvantel() {
		return telAvantel;
	}
	public void setTelAvantel(String telAvantel) {
		this.telAvantel = telAvantel;
	}
	public String getTelTelmex() {
		return telTelmex;
	}
	public void setTelTelmex(String telTelmex) {
		this.telTelmex = telTelmex;
	}
	public Integer getOdtFolioTelecarga() {
		return odtFolioTelecarga;
	}
	public void setOdtFolioTelecarga(Integer odtFolioTelecarga) {
		this.odtFolioTelecarga = odtFolioTelecarga;
	}
	public String getModModelo() {
		return modModelo;
	}
	public void setModModelo(String modModelo) {
		this.modModelo = modModelo;
	}
	public String getcTram() {
		return cTram;
	}
	public void setcTram(String cTram) {
		this.cTram = cTram;
	}
	public String getTerId() {
		return terId;
	}
	public void setTerId(String terId) {
		this.terId = terId;
	}
	public String getTerIdEncr() {
		return terIdEncr;
	}
	public void setTerIdEncr(String terIdEncr) {
		this.terIdEncr = terIdEncr;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getComPoblacion() {
		return comPoblacion;
	}
	public void setComPoblacion(String comPoblacion) {
		this.comPoblacion = comPoblacion;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public Date getFechaTelecarga() {
		return fechaTelecarga;
	}
	public void setFechaTelecarga(Date fechaTelecarga) {
		this.fechaTelecarga = fechaTelecarga;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "TeleReport [id=" + id + ", aplDescripción=" + aplDescripción + ", bcoNombre=" + bcoNombre
				+ ", comAfiliación=" + comAfiliación + ", comNombre=" + comNombre + ", comDomicilio=" + comDomicilio
				+ ", telLocal=" + telLocal + ", telAvantel=" + telAvantel + ", telTelmex=" + telTelmex
				+ ", odtFolioTelecarga=" + odtFolioTelecarga + ", modModelo=" + modModelo + ", cTram=" + cTram
				+ ", terId=" + terId + ", terIdEncr=" + terIdEncr + ", proveedor=" + proveedor + ", comPoblacion="
				+ comPoblacion + ", numSerie=" + numSerie + ", fechaTelecarga=" + fechaTelecarga + "]";
	}
	
	
    
    
    

}


