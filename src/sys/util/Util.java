package sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	
	/**
	 * METODO QUE CONVIERTE UNA FECHA DE UTIL DATE A SQL DATE FORMATO(dd/MM/yyyy)
	 * @param java.util.Date
	 * @return  java.sql.Date
	 */
	public java.sql.Date addDateFormat(java.util.Date test){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		 
		java.util.Date dt;		
		java.sql.Date sqlDate = null;
		try {
			dt = format.parse(format.format(test));			
			sqlDate = new java.sql.Date(dt.getTime());
			return sqlDate;
		}
		catch (ParseException e) {
			System.out.println("mensaje parse" +e.getMessage());
			System.out.println("causa" +e.getCause());
			return null;
		}
	}
	
	/**
	 * METODO QUE CONVIERTE UNA FECHA DE STRING A SQL DATE FORMATO(dd/MM/yyyy)
	 * @param java.util.Date
	 * @return  java.sql.Date
	 */
    public java.sql.Date stringToSqlDate(String Fecha) {
        Date date = null;
        try 
        {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(Fecha);
            java.sql.Date insertDate = new java.sql.Date(date.getTime());
            return insertDate;
        }
        catch (ParseException ex) {
            System.out.println("Error al convertir string a fecha java.sql.Date");
        }
        return null;       
        
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
	 * METODO QUE GENERA UN ARCHIVO TXT EN C://TelechargeLogs//LogFile.txt"
	 * @param codigoError
	 * @param mensajeError
	 */
   public void writeFile(String codigoError, String mensajeError){
	   try { 
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
   
   /**
    * METODO PARA ENCRIPTACION EN MD5
    * @param stringAEncriptar
    * @return
    * @throws NoSuchAlgorithmException 
    */
   public String encriptaEnMD5(String stringAEncriptar) throws NoSuchAlgorithmException
   {
       char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
       MessageDigest msgd = MessageDigest.getInstance("MD5");
       byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
       StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
       for (int i = 0; i < bytes.length; i++)
       {
           int bajo = (int)(bytes[i] & 0x0f);
           int alto = (int)((bytes[i] & 0xf0) >> 4);
           strbCadenaMD5.append(CONSTS_HEX[alto]);
           strbCadenaMD5.append(CONSTS_HEX[bajo]);
       }
       return strbCadenaMD5.toString();
   }
    
	
}
