package sys.dao;

import java.util.List;
import sys.model.Empleado;

public interface EmpleadoDao {
	
	public List<Empleado> listarEmpleados();
	public Integer agregarEmpleado(Empleado e);
	public void modificarEmpleado(Empleado e);
	public void eliminarEmpleado(Integer id);
	
}
