package sys.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TELE_REPORT")
public class TeleReport implements Serializable {

	private static final long serialVersionUID = -8891923786311280894L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementoTeleReport")
	@SequenceGenerator(
	    name="incrementoTeleReport",
		sequenceName="idTeleReport_seq",
		allocationSize=1)
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
    @Column(name = "fechaRecarga")
    private Date    fechaRecarga; 
    @Column(name = "pbiNombre")
    private String  pbiNombre; 
    @Column(name = "res")
    private String  res; 
    @Column(name = "resExt")
    private String  resExt;            
    @Column(name = "cBase")
    private String  cBase;               
    @Column(name = "cProfile")
    private String  cProfile; 
    @Column(name = "horaInicio")
    private Date    horaInicio; 
    @Column(name = "horaFin")
    private Date    horaFin; 
    @Column(name = "version")
    private String  version; 
    @Column(name = "fiid")
    private String  fiid; 
    @Column(name = "facturacion")
    private String  facturacion; 
    @Column(name = "cveInstalador")
    private String  cveInstalador; 
	
    public TeleReport() {
		
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
	public Date getFechaRecarga() {
		return fechaRecarga;
	}

	public void setFechaRecarga(Date fechaRecarga) {
		this.fechaRecarga = fechaRecarga;
	}

	public String getPbiNombre() {
		return pbiNombre;
	}
	public void setPbiNombre(String pbiNombre) {
		this.pbiNombre = pbiNombre;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getResExt() {
		return resExt;
	}
	public void setResExt(String resExt) {
		this.resExt = resExt;
	}
	public String getcBase() {
		return cBase;
	}
	public void setcBase(String cBase) {
		this.cBase = cBase;
	}
	public String getcProfile() {
		return cProfile;
	}
	public void setcProfile(String cProfile) {
		this.cProfile = cProfile;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getFiid() {
		return fiid;
	}
	public void setFiid(String fiid) {
		this.fiid = fiid;
	}
	public String getFacturacion() {
		return facturacion;
	}
	public void setFacturacion(String facturacion) {
		this.facturacion = facturacion;
	}
	public String getCveInstalador() {
		return cveInstalador;
	}
	public void setCveInstalador(String cveInstalador) {
		this.cveInstalador = cveInstalador;
	}
	
	
    
    
    

}


