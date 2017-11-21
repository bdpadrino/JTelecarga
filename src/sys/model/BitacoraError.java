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
@Table(name = "bitacora_errores")
public class BitacoraError {
	
	@Id
    @Column(name = "id")													//ID DE LA BITACORA
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementoBitacora")
	@SequenceGenerator(
	    name="incrementoBitacora",
	    sequenceName="trans_seq",
	    allocationSize=1
	)
	private Integer id;  	
  	@Column(name = "codigoError")			
	private String	codigoError;
	@Column(name = "mensajeError")	
	private String	mensajeError;
    @Column(name = "fecha")			
	private Date 	fecha;
	
    public BitacoraError() {
    
    }
    
    public BitacoraError(Integer id, String codigoError, String mensajeError, Date fecha) {
		super();
		this.id = id;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
		this.fecha = fecha;
	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "BitacoraErrores [id=" + id + ", codigoError=" + codigoError + ", mensajeError=" + mensajeError
				+ ", fecha=" + fecha + "]";
	}

	public String createErrorCode(String ubi, String tip, String err) {
    	String codigoError = "E_"+ubi+"_"+tip+"_"+err;
    	System.out.println("codigo de error = "+codigoError);
    	return codigoError;
    }
  
}