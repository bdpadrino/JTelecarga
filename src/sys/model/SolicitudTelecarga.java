package sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SOLICITUD_TELECARGA")
public class SolicitudTelecarga {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementoSolicitudTelecarga")
	@SequenceGenerator(
	    name="incrementoSolicitudTelecarga",
		sequenceName="idSolicitudTelecarga_seq",
		allocationSize=1)
    @Column(name = "idSolicitud")
	private int 	id;		
	@Column(name = "folio")
	private Integer numeroFolio; 
	@Column(name = "marca")
	private String 	marca;
	@Column(name = "versión")
	private Double	version;
	@Column(name = "modelo")
	private String 	modelo;
	@Column(name = "tipo_aplicacion")
	private String 	tipoAplicacion;
	@Column(name = "fecha_solicitud")
	private Date 	fechaSolicitud;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getNumeroFolio() {
		return numeroFolio;
	}
	public void setNumeroFolio(Integer numeroFolio) {
		this.numeroFolio = numeroFolio;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Double getVersion() {
		return version;
	}
	public void setVersion(Double version) {
		this.version = version;
	}
	public String getTipoAplicacion() {
		return tipoAplicacion;
	}
	public void setTipoAplicacion(String tipoAplicacion) {
		this.tipoAplicacion = tipoAplicacion;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	@Override
	public String toString() {
		return "TerminalInfo [id=" + id + ", numeroFolio=" + numeroFolio + ", marca=" + marca + ", version=" + version
				+ ", modelo=" + modelo + ", tipoAplicacion=" + tipoAplicacion + ", fechaSolicitud=" + fechaSolicitud
				+ "]";
	}
	
	
	
}
