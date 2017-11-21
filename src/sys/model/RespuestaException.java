
package sys.model;

/**
 *
 * @author bdpad
 */
public class RespuestaException {
    
    private String mensaje;
    
    private String accion;
    
    private Boolean exitoso;
    
    public RespuestaException() {
    }

    public RespuestaException(String accion, String mensaje, Boolean exitoso) {
        this.accion =  accion;
        this.mensaje = mensaje;
        this.exitoso = exitoso;

    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getExitoso() {
        return exitoso;
    }

    public void setExitoso(Boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
}
