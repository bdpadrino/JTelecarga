
package sys.util;
/*
import sys.model.RespuestaException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.hibernate.JDBCException;*/

/**
 *
 * @author bdpad
 */
public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1167935269032567623L;
    

    /* RespuestaException respuesta = new RespuestaException();
    private Throwable causa;
    
    public CustomException(){       
    }
    
    public CustomException(String mensaje){
        super(mensaje);
    }

    
    public Throwable getCausa() {
		return causa;
	}

	public void setCausa(Throwable causa) {
		this.causa = causa;
	}
	

	public RespuestaException getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaException respuesta) {
		this.respuesta = respuesta;
	}

	public RespuestaException CustomException(String mensaje, JDBCException e) {
        System.err.println("Error JDBC" +e.getSQLException().getMessage().replace("ERROR:","") +""+ e.getSQLException().getSQLState());
        respuesta.setAccion(mensaje);
        respuesta.setExitoso(false);
        //ERRORES DE TRIGGERS
        if (e.getSQLException().getSQLState().equals("P0001"))
            respuesta.setMensaje(e.getSQLException().getMessage().replace("ERROR:",""));
        //ERROR DE UNIQUE
        if (e.getSQLException().getSQLState().equals("23505"))
            respuesta.setMensaje("Error al Insertar, la cedula ya existe");/
        //ERROR DE ... 
        if (e.getSQLException().getSQLState().equals("28P01"))
            respuesta.setMensaje("Error: "+e.getSQLException().getMessage().replace("FATAL:",""));
        //ERRROR DE CONEXION A BD
        if (e.getSQLException().getSQLState().equals("3D000"))
             respuesta.setMensaje("Error de conexion con base de Datos: "+e.getSQLException().getMessage().replace("FATAL:",""));
        return respuesta;
    }

    public RespuestaException CustomException(String mensaje, NumberFormatException e) {
        System.err.println("Error NumberFormatException  " +e.getMessage());
        respuesta.setAccion(mensaje);
        respuesta.setMensaje("Este campo solo puede tener numeros " +e.getMessage());
        respuesta.setExitoso(false);
        return respuesta; 
    }

   
    public RespuestaException CustomException(String mensaje, ParseException e) {
        System.err.println("Error ParseException " +e.getMessage().replace("Unparseable date","Fecha Erronea"));
        respuesta.setAccion(mensaje);
        respuesta.setMensaje("Error al "+mensaje+" " +e.getMessage().replace("Unparseable date","Fecha Erronea"));
        respuesta.setExitoso(false);
        return respuesta;
    }   
        
    public RespuestaException CustomException(String mensaje,FileNotFoundException  e) {
        System.err.println("Error FileNotFoundException Archivo no encontrado " +e.getMessage());
        respuesta.setAccion(mensaje);
        respuesta.setMensaje("Error al "+mensaje+" Archivo no encontrado "+e.getMessage());
        respuesta.setExitoso(false);    
        return respuesta;
    }
    
    public RespuestaException CustomException(String mensaje,IOException  e) {
        System.err.println("Error IOException Lectura/Escritura " +e.getMessage());
        respuesta.setAccion(mensaje);
        respuesta.setMensaje("Error al "+mensaje+" Empleado " +e.getMessage());
        respuesta.setExitoso(false);    
        return respuesta;
    }
    
    public RespuestaException CustomException(String mensaje, Exception e) {
        System.err.println("Error Exception " +e.getMessage());
        respuesta.setAccion(mensaje);
        respuesta.setMensaje("Error al "+mensaje+" Empleado " +e.getMessage());
        respuesta.setExitoso(false);
        return respuesta;
    }*/
    
    
}


