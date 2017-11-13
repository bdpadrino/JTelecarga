package sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
}
