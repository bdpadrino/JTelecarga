package sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	 * @return fecha y hora en String
	 */
    public String StringTimeStampNow(){
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.MINUTE, 30);
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
        return sdf.format(ourJavaTimestampObject);
    }
    
    /**
   	 * METODO QUE DEVUELVE MES Y ANIO ACTUAL
   	 * @return fecha y hora en String
   	 */
       public String currentYearMonth(){
           Calendar calendar = Calendar.getInstance();
           //calendar.add(Calendar.MINUTE, 30);
           java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

           SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
           return sdf.format(ourJavaTimestampObject);
       }
       
       /**
        * METODO QUE DEVUELVE LA CANTIDAD DE DIAS DEL MES
        * @return
        */
       public int daysInAMonth() {
    	   Calendar calendar = Calendar.getInstance();
           java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
           SimpleDateFormat sdf = new SimpleDateFormat("MM");
           String mes = sdf.format(ourJavaTimestampObject);
           System.out.println("mes " +mes);
           if (mes.equals("01"))
        	   return 31;
		   if (mes.equals("02"))
			   return 28;
		   if (mes.equals("03"))
			   return 31;
		   if (mes.equals("04"))
			   return 31;
		   if (mes.equals("05"))
			   return 31;
		   if (mes.equals("06"))
			   return 30;
		   if (mes.equals("07"))
			   return 31;
		   if (mes.equals("08"))
			   return 30;
		   if (mes.equals("09"))
			   return 30;
		   if (mes.equals("10"))
			   return 31;
		   if (mes.equals("11"))
			   return 30;
		   if (mes.equals("12"))   
			   return 31;
        	return 1;	   
       }
    
    /**
     * METODO PARA INSERTAR TIMESTAMP EN BD
     * @return java.sql.Timestamp
     */ 
    public java.sql.Timestamp insertSqlTimeStamp() {
        try 
        {
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
            return ourJavaTimestampObject;
        }
        catch (Exception ex) {
            System.err.println("Error al convertir string a fecha timestamp" +ex.getMessage());
            return null;
        }
           
    }
	
	 // Suma los días recibidos a la fecha  
	  public Date sumarDiasFecha(Date fecha, int dias){
	       Calendar calendar = Calendar.getInstance();
	       calendar.setTime(fecha); 					// Configuramos la fecha que se recibe
	       calendar.add(Calendar.DAY_OF_YEAR, dias);  	// numero de días a añadir, o restar en caso de días<0
	       return calendar.getTime(); 					// Devuelve el objeto Date con los nuevos días añadidos
	  }
    
	
		/**
		 * METODO QUE GENERA UN ARCHIVO TXT EN C://TelechargeLogs//LogFile.txt
		 * @param Fecha
		 * @param codigoError
		 * @param mensajeError
		 */
	   public void writeFile(String Fecha, String codigoError, String mensajeError){
		   try { 
		        String ruta = "C://TelechargeLogs//LogFile.txt";
		        File archivo = new File(ruta);
		        BufferedWriter bw;
		        if(archivo.exists()) {
		            bw = new BufferedWriter(new FileWriter(archivo,true));	            
		            bw.write(Fecha+" "+codigoError+" "+mensajeError );
		            bw.newLine();
		        } else {
		            bw = new BufferedWriter(new FileWriter(archivo));
		            bw.write(Fecha+" "+codigoError+" "+mensajeError );
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
   public String encriptWithMD5(String stringAEncriptar) throws NoSuchAlgorithmException
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
    
   /**
    * METODO CREADO PARA GENERAR UN PASWWORD ALEATORIO 
    * @return
    */
   public String generatePassword () {
		Random rnd = new Random();
		String abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String cadena ="";
		int pos=0, num;
		//BUSQUEDA ALEATORIA DE UNA POSICION ENTRE LA LONGITUD DEL ABECEDARIO
		pos = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);
		//BUSQUEDA ALEATORIA DE 4 DIGITOS NUMERICOS
		num = (int) (rnd.nextDouble() * 9999 + 1000);
		cadena = cadena + abecedario.charAt(pos) + num;
		pos = (int) (rnd.nextDouble() * abecedario.length() - 1 + 0);
		cadena = cadena + abecedario.charAt(pos);
		
		return cadena;
	}
   
    
   /**
    * METODO USADO PARA ENVIAR CORREOS CON ACORDE VIA SSL CON PARAMETROS DE ENVIO
    * @param username
    * @param password
    * @param asunto
    * @param cuerpo
    * @param destinatario
    */
   public void sendMailSSLAcorde (String username, String password,String host, int puerto, String asunto, String cuerpo, String dirEnvio, String destinatario) {
	   	System.out.println("username "+ username + " password " + password + " host " + host + " puerto " +puerto +" asunto " +asunto +" cuerpo " +cuerpo +" dirEnvio "+dirEnvio+ " destinatario "+ destinatario);
	    String u = "brayan.padrino@acorde.com.ve";
		   String p = "Rusia3890**";
	   	
	   	
		Properties props = new Properties();
		props.put("mail.smtp.host", "a2plcpnl0436.prod.iad2.secureserver.net");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		/*props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", puerto);
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", puerto);*/

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(u,p);
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("Mensaje MessagingE "+e.getMessage());
	    	System.out.println("Causa   MessagingE "+e.getCause());
			throw new RuntimeException(e);			
		}
	}
   
   /**
   * METODO USADO PARA ENVIAR CORREOS CON GMAIL VIA SSL CON PARAMETROS DE ENVIO
   * @param username
   * @param password
   * @param asunto
   * @param cuerpo
   * @param destinatario
   */
   public void sendMailSSLGmail (String username, String password, String asunto, String cuerpo, String destinatario) {
   	Properties props = new Properties();
   	props.put("mail.smtp.host", "smtp.gmail.com");
   	props.put("mail.smtp.socketFactory.port", "465");
   	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
   	props.put("mail.smtp.auth", "true");
   	props.put("mail.smtp.port", "465");

   	Session session = Session.getDefaultInstance(props,
   		new javax.mail.Authenticator() {
   			protected PasswordAuthentication getPasswordAuthentication() {
   				return new PasswordAuthentication(username,password);
   			}
   		});

   	try {

   		Message message = new MimeMessage(session);
   		message.setFrom(new InternetAddress("prosa@noresponder.com"));
   		message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(destinatario));
   		message.setSubject(asunto);
   		message.setText(cuerpo);

   		Transport.send(message);

   		System.out.println("Done");

   	} catch (MessagingException e) {
   		System.out.println("Mensaje MessagingE "+e.getMessage());
       	System.out.println("Causa   MessagingE "+e.getCause());
   		throw new RuntimeException(e);			
   	}
   }
   
}
