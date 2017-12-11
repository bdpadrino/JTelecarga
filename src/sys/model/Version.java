package sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "control_versiones")
public class Version {
	 
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementIdVersion")
		@SequenceGenerator(
		    name="incrementIdVersion",
			sequenceName="idVersion_seq",
			allocationSize=1)
    	@Column(name = "id")
		private Integer id;
		@Column(name = "tipoaplicacion")
		private String tipoAplicacion;
		@Column(name = "modelo")
		private String modelo;  	    
		@Column(name = "versión")
		private Integer version; 
		    	    
	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTipoAplicacion() {
			return tipoAplicacion;
		}

		public void setTipoAplicacion(String tipoAplicacion) {
			this.tipoAplicacion = tipoAplicacion;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public Integer getVersion() {
			return version;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

		@Override
		public String toString() {
			return "Versiones [id=" + id + ", tipoAplicacion=" + tipoAplicacion + ", modelo=" + modelo + ", version="
					+ version + "]";
		}
	    
	   


		
		
		
}
