package sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "terminal_info")
public class TerminalInfo {
	
	@Id
    @Column(name = "id")
	private int 	id;		
	@Column(name = "folio")
	private Integer numeroFolio; 
	@Column(name = "terminal")
	private String 	terminal; 		
	@Column(name = "marca")
	private String 	marca;
	@Column(name = "versión")
	private Double	version;
	@Column(name = "modelo")
	private String 	modelo;
	@Column(name = "tipo_aplicacion")
	private String 	tipo_aplicacion;
	@Column(name = "fecha_solicitud")
	private Date 	fecha_solicitud;
	
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
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
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
	public String getTipo_aplicacion() {
		return tipo_aplicacion;
	}
	public void setTipo_aplicacion(String tipo_aplicacion) {
		this.tipo_aplicacion = tipo_aplicacion;
	}
	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}
	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}
	/*public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}*/
	public Double getVersion() {
		return version;
	}
	public void setVersion(Double version) {
		this.version = version;
	}
	
	

	
	
	
}
