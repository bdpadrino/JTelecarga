package sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONTROL_VERSIONES")
public class TerminalVersion {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementoControlVersiones")
	@SequenceGenerator(
	    name="incrementoControlVersiones",
		sequenceName="idControlVersiones_seq",
		allocationSize=1)
    @Column(name = "id")
	private int 	id;	
	@Column(name = "tipoAplicacion")
	private String 	tipoAplicacion;
	@Column(name = "versión")
	private Double	version;
	@Column(name = "modelo")
	private String 	modelo;
	

	public TerminalVersion() {
	
	}
	
	public TerminalVersion(int id, String tipoAplicacion, Double version, String modelo) {
		super();
		this.id = id;
		this.tipoAplicacion = tipoAplicacion;
		this.version = version;
		this.modelo = modelo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoAplicacion() {
		return tipoAplicacion;
	}
	public void setTipoAplicacion(String tipoAplicacion) {
		this.tipoAplicacion = tipoAplicacion;
	}

	public Double getVersion() {
		return version;
	}
	public void setVersion(Double version) {
		this.version = version;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public String toString() {
		return "TerminalVersion [id=" + id + ", tipo_aplicacion=" + tipoAplicacion + ", version=" + version
				+ ", modelo=" + modelo + "]";
	}


	
}
