package sys.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "control_versiones")
public class TerminalVersion {

	@Id
    @Column(name = "id")
	private int 	id;	
	@Column(name = "tipo_aplicacion")
	private String 	tipo_aplicacion;
	@Column(name = "terminal_version")
	private Double	version;
	@Column(name = "modelo")
	private String 	modelo;
	

	public TerminalVersion() {
	
	}
	
	public TerminalVersion(int id, String tipo_aplicacion, Double version, String modelo) {
		super();
		this.id = id;
		this.tipo_aplicacion = tipo_aplicacion;
		this.version = version;
		this.modelo = modelo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo_aplicacion() {
		return tipo_aplicacion;
	}
	public void setTipo_aplicacion(String tipo_aplicacion) {
		this.tipo_aplicacion = tipo_aplicacion;
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
		return "TerminalVersion [id=" + id + ", tipo_aplicacion=" + tipo_aplicacion + ", version=" + version
				+ ", modelo=" + modelo + "]";
	}


	
}
