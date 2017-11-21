package sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Util {
	
	public java.sql.Date addDateFormat(java.util.Date test){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		 
		java.util.Date dt;		
		java.sql.Date sqlDate = null;
		try {
			dt = format.parse(format.format(test));			
			sqlDate = new java.sql.Date(dt.getTime());
			System.out.println("fecha acomodada"+sqlDate);
			return sqlDate;
		}
		catch (ParseException e) {
			System.out.println("mensaje parse" +e.getMessage());
			System.out.println("causa" +e.getCause());
			return null;
		}
	}
	
	/**
	 * METODO QUE DEVUELVE LA FECHA Y HORA ACTUAL
	 * @return
	 */
    public String impresionTimeStampNow(){
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MINUTE, 30);
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
        return sdf.format(ourJavaTimestampObject);
    }
    

	
   /**
    * 
    * @throws IOException
    */
   public void writeFile(String codigoError, String mensajeError){
	   try { 
		    /*String pathFile = new File(".").getCanonicalPath();
		    String pathFileA = new File(".").getAbsolutePath()+"/src/sys/"+nombreArchivo;
		    String pathFilep = new File(".").getPath()+"/src/"+nombreArchivo;
		    String path2 = System.getProperty("user.dir");
		    
		    System.out.println("path2" +path2);		
		    System.out.println("rutaArchivo" +pathFile);		
		    System.out.println("rutaArchivoA" +pathFileA);
		    System.out.println("rutaArchivoP" +pathFilep);*/
	        String ruta = "C://TelechargeLogs//LogFile.txt";
	        File archivo = new File(ruta);
	        BufferedWriter bw;
	        if(archivo.exists()) {
	            bw = new BufferedWriter(new FileWriter(archivo,true));	            
	            bw.write(codigoError+" "+mensajeError );
	            bw.newLine();
	        } else {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(codigoError+" "+mensajeError );
	            bw.newLine();
	        }
	        bw.close();
	   } 
	   catch(SecurityException e) {
		   System.out.println("error SE"+e.getMessage() +" causa "+e.getCause());
	   }
	   catch(IOException e) {
		   System.out.println("error IO"+e.getMessage() +" causa "+e.getCause());
	   }
	   catch(Exception e) {
		   System.out.println("error E"+e.getMessage() +" causa "+e.getCause());
	   }
    }
   
    
	
}
