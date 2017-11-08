package sys.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "empleado1")
public class Empleado implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -304779055358243427L;

	@Id
    @Column(name = "cedula")
    private Integer cedula;

    @Column(name = "nombre")
    private Integer nombre;

    @Column(name = "apellido")
    private Integer apellido;

    public Empleado() {
    }

    public Empleado(Integer id, Integer name,Integer apellido) {
        this.cedula = id;
        this.nombre = name;
        this.apellido = apellido;
    }

    public Integer getCedula() {
            return cedula;
    }
    public void setCedula(Integer Cedula) {
            cedula = Cedula;
    }
    public Integer getNombre() {
            return nombre;
    }
    public void setNombre(Integer Nombre) {
            nombre = Nombre;
    }
    public Integer getApellido() {
            return apellido;
    }
    public void setApellido(Integer Apellido) {
            apellido = Apellido;
    }

	
}