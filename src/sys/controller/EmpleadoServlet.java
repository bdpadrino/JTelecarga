package sys.controller;

import java.io.*;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

import sys.dao.imp.EmpleadoDaoImp;

public class EmpleadoServlet extends HttpServlet {
    
    public Map<String, String> response = new HashMap<>();
    
    private static final long serialVersionUID = -3347582369726041362L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("PARAMETRO RECIBIDO POR DOPOST " + req.getParameter("parametro"));
        if (req.getParameter("parametro").equals("1")) {
           /* response.put("action", "1");
            response.put("status", "success");
            req.setAttribute("server_response", response);*/
            Leer(req, res);
        }
           
    }
       
    /**
     * METODO PARA VER TRABAJADORES
     * @param req
     * @param res
     */
    public void Leer(HttpServletRequest req, HttpServletResponse res) {
    	System.out.println("Entrando a Leer");
    	EmpleadoDaoImp ce = new EmpleadoDaoImp();       
       // Empleado emp= new Empleado();
        try 
        {
        	
            req.setAttribute("listaEmpleadosPrueba", ce.listarEmpleados());   
            System.out.println("saliendo de leer");
            req.getRequestDispatcher("./Empleado.jsp").forward(req, res);
        } 
        catch (ServletException | IOException e) {
            System.out.println("Error al Leer " + e);
        }
    }

   

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getParameter("parametro").equals("1")) {
           response.put("status", "success");
           response.put("action", "1");
           req.setAttribute("server_response", response);
           Leer(req,res);
        }
    }
}
