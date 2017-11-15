package sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    
    
	
}
