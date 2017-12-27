
package sys.model;

/**
 *
 * @author bdpad
 */
public class RespuestaException {
    
    private String mensaje;
    
    private String codigoError;
    
    public RespuestaException() {
    }

    public RespuestaException(String mensaje, String codigoError) {
		super();
		this.mensaje = mensaje;
		this.codigoError = codigoError;
	}


	public String getCodigoError() {
		return codigoError;
	}


	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}


	public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
}
