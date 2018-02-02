//UN1Q
package sys.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.JDBCException;

import sys.dao.BduACTDao;
import sys.dao.BduDao;
import sys.dao.BitacoraErrorDao;
import sys.dao.CardInfoDao;
import sys.dao.TeleReportDao;
import sys.dao.TelecargaSTDao;
import sys.dao.TransactionDao2;
import sys.dao.imp.*;
import sys.model.Bdu;
import sys.model.BduACT;
import sys.model.BitacoraError;
import sys.model.CardInfo;
import sys.model.RespuestaException;
import sys.model.RespuestaTransaction;
import sys.model.TeleReport;
import sys.model.TelecargaACT;
import sys.model.TelecargaST;
import sys.model.SolicitudTelecarga;
import sys.model.Transaction;
import sys.model.Transaction2;
import sys.util.*;

@Path("/prosa")
public class TransactionService {

	
	@POST
    @Path("/prueba")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaTransaction pruebaCon(String id) {
		RespuestaTransaction r = new RespuestaTransaction();
		r.setIso("123");
		r.setMessage("HOla recibido"+id);
		r.setStatus("OK");
		return r;
	}
	
	@POST
    @Path("/telecarga")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> pruebaTelecarga(SolicitudTelecarga stEntrante) {
		TelecargaACT telecargaACT = new TelecargaACT();
	    TelecargaST telecargaST = new TelecargaST();
	    Bdu bdu = new Bdu();
	    BduACT bduACT = new BduACT();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		RespuestaException respuesta= new RespuestaException();

        TelecargaSTDaoImp cST = new TelecargaSTDaoImp();  
        TelecargaACTDaoImp cACT = new TelecargaACTDaoImp();  
        SolicitudTelecargaDaoImp cSolT = new SolicitudTelecargaDaoImp();
        TerminalVersionDaoImp ctv = new TerminalVersionDaoImp();
        
        BduACTDao cBduACT =new BduACTDaoImp();
        BduDao cBdu =new BduDaoImp();
        	       
        try 
        {
        	System.out.println("Solicitud de Telecarga Recibida numero de Folio:  "+stEntrante.getNumeroFolio());	   
	        //BUSCAR EN ACT LA INFORMACION SEGUN FOLIO	        
	        telecargaACT = cACT.findTelecargaByFolio(stEntrante.getNumeroFolio()); 
	        if (telecargaACT != null){
	        	if(telecargaACT.getTerminalMark().trim().equals(stEntrante.getMarca().trim())) {
	        		System.out.println("igual la marca");
	        		if(telecargaACT.getTerminalModel().equals(stEntrante.getModelo())) {
	        			System.out.println("igual el modelo");
			        	
	        			
	        			//BUSCA LA ULTIMA VERSION DE UNA APLICACION SEGUN MODELO Y TIPO APP
			        	Double version = ctv.findLastVersion(stEntrante.getModelo(), stEntrante.getTipoAplicacion());
			        	System.out.println("Ultima version disponible en ST para este modelo y tipo de app "+version+" version actual en POS "+stEntrante.getVersion());
			        	telecargaST.setUpdateApp(false);
			        	telecargaST.setStatus("Sin Finalizar");
			        	if (version > stEntrante.getVersion() ) {
			        		System.out.println("SE DEBE ACTUALIZAR A LA VERSION "+version);
			        		telecargaST.setUpdateApp(true);
			        		telecargaST.setVersionToDownload(version);
			        	}
			        	else {
			        		System.out.println("EL TERMINAL YA ESTA ACTUALIZADO A LA ULTIMA VERSION DIPONIBLE: " +version);
			        		telecargaST.setUpdateApp(false);
			        		telecargaST.setVersionToDownload(null);
		        		}
			        	
			        	//REVISA SI LA APP LLEVA BDU 
			        	if(telecargaACT.getComMultimerchant().equals(1)) {
			        		bduACT = cBduACT.findBduACTByFolioInACT(stEntrante.getNumeroFolio());
			        			if (bduACT != null) {
			        			addBduACTinST(bduACT, bdu);
				        		cBdu.addBdu(bdu);
				        		telecargaST.setBduParameters(bdu);
				        		System.out.println("BDU encontrado");
			        		}
			        		else {
			        			System.out.println("BDU BUSCADO Y NO ENCONTRADO EN ACT");
			        			generateBitacora("ST", "INF", "NoVal","BDU NO ENCONTRADO EN ACT");
			        			return getWrongResponse(mapParameter, respuesta, "520", "BDU NO ENCONTRADO EN ACT");
			        		}				        
			        	}
			        	
			        	//REGISTRO LA SOLICITUD 
			        	Util util = new Util();
			        	stEntrante.setFechaSolicitud(util.insertSqlTimeStamp());
			        	cSolT.addSolicitudTelecarga(stEntrante);
			        	
			        	//SE REGISTRA LA INFORMACION DE ACT EN ST
			        	addTelecargaACTinST(telecargaACT, telecargaST);
			        	System.out.println("AGREGADO EN ST "+telecargaST.toString());
			        	cST.addTelecarga(telecargaST);	
	        		}
	        		else {
	        			//ERROR INFORMATIVO LOS MODELOS NO SON IGUALES
	        			System.out.println("ERROR INFORMATIVO El modelo no coincide");
	        			generateBitacora("ST", "INF", "NoVal","El modelo no coincide");	
	        			return getWrongResponse(mapParameter, respuesta, "521", "EL MODELO NO COINCIDE");
	        		}
	        	}
	        	else {
	        		//ERROR INFORMATIVO LAS MARCAS NO SON IGUALES
	        		System.out.println("ERROR INFORMATIVO Las marcas no coinciden "+stEntrante.getMarca() +"TELECARGA EN ACT"+ telecargaACT.getTerminalMark());
	        		generateBitacora("ST", "INF", "NoVal","Las marcas no coinciden");
	        		return getWrongResponse(mapParameter, respuesta, "522", "LAS MARCAS NO COINCIDEN");
	        	}		        		
	        }
	        else {
	        	//ERROR INFORMATIVO FOLIO RECIBIDO DE POS NO SE ENCUENTRA EN ACT
	        	System.out.println("ERROR INFORMATIVO FOLIO RECIBIDO DE POS " +stEntrante.getNumeroFolio()+" NO SE ENCUENTRA EN ACT");
	        	generateBitacora("ST", "INF", "NoVal","Folio recibido del terminal no se encuentra en ACT");
	        	return getWrongResponse(mapParameter, respuesta, "523", "FOLIO RECIBIDO DE POS NO SE ENCUENTRA EN ACT");
	        }
        }
        catch(JDBCException e) {
			System.out.println("Sql State "+e.getSQLException().getSQLState());
			System.out.println("Eror Code "+e.getSQLException().getErrorCode());
			//UNIQUE CONSTRAINT ERROR CODE 1
			if (e.getSQLException().getErrorCode() == 1) {
				generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
			}
			//MANDATORIEDAD ERROR CODE 1400
			if (e.getSQLException().getErrorCode() == 1400) {
				System.out.println("entro aqui");
				generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
			}
			//CAMPO NO ENCONTRADO EN BD ERROR CODE ORA-00904
			if (e.getSQLException().getErrorCode() == 904) {
				generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
			}
			
			return getWrongResponse(mapParameter, respuesta, "524", e.getCause().toString());
		}
        catch(Exception e){
            System.out.println("Error 505 MENSAJE	" +e.getMessage());
            System.out.println("Error 505 CAUSA		" +e.getCause());
            generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
            return getWrongResponse(mapParameter, respuesta, "503", e.getCause().toString());
        }
        //SE DEVUELVEN LOS PARAMETROS
        mapParameter.put("Telecarga", telecargaST);
        return mapParameter;
        

    }
	
	@POST
    @Path("/confirmarTelecarga")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void guardarEnTeleReport(TeleReport trEntrante) {
		TeleReportDao  ctr = new TeleReportDaoImp();		
		try {
			ctr.addTeleReport(trEntrante);
		}
		catch(JDBCException e) {
			System.out.println("Sql State "+e.getSQLException().getSQLState());
			System.out.println("Eror Code "+e.getSQLException().getErrorCode());
			//UNIQUE CONSTRAINT ERROR CODE 1
			if (e.getSQLException().getErrorCode() == 1) {
				generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
			}
			//MANDATORIEDAD ERROR CODE 1400
			if (e.getSQLException().getErrorCode() == 1400) {
				generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
			}
		}
        catch(Exception e){
            System.out.println("Error 505 MENSAJE	" +e.getMessage());
            System.out.println("Error 505 CAUSA		" +e.getCause());
            generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
        }
	}
	
	@POST
    @Path("/confirmarTelecarga1")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String guardarEnTeleReport1(String idTelecarga) { 
		System.out.println("Entrando "+idTelecarga);
		TelecargaST telecarga = new TelecargaST();
		TelecargaSTDao ct = new TelecargaSTDaoImp();

		String mensaje ="";
		
		TeleReport telereport = new TeleReport();
		TeleReportDao  ctr = new TeleReportDaoImp();
		
		try {
			System.out.println("Entrando2");
			telecarga = ct.findByID(Integer.parseInt(idTelecarga));
			if (telecarga == null) {
				mensaje = "El id de Telecarga no se encuentra Registrado";
				System.out.println("Entrando3");
			}
			else {
				System.out.println("telecarga"+telecarga.toString());
				telereport.setAplDescripción(telecarga.getAppDesc());
				telereport.setBcoNombre(telecarga.getBnkName());
				telereport.setComAfiliación(telecarga.getMembershipComm());
				telereport.setComNombre(telecarga.getCommerceName());
				telereport.setComDomicilio(telecarga.getCommerceAddres());
				telereport.setTelLocal(telecarga.getAuthPhoneNumber());
				telereport.setTelAvantel(telecarga.getAvantelPhoneNumber());
				telereport.setTelTelmex(telecarga.getTelmexPhoneNumber());
				telereport.setOdtFolioTelecarga(telecarga.getTcFolio());
				telereport.setModModelo(telecarga.getTerminalModel());
				telereport.setcTram(telecarga.getPaperKey());
				telereport.setTerId(telecarga.getTerId());
				telereport.setTerIdEncr(telecarga.getEncrTermId());
				telereport.setProveedor(telecarga.getTerminalMark());
				telereport.setComPoblacion("");
				telereport.setNumSerie("");
				telereport.setFechaTelecarga(telecarga.getOrderDate());
				telereport.setRes("");
				telereport.setResExt("");												//ACA SE GRABA LA DIRECCION DE IP DEL POS
				telereport.setcBase("0");												//1 SI SE REALIZA CARGA BASE 
				telereport.setcProfile(telecarga.getUpdateApp().toString());			//1 SI SE REALIZA CARGA PROFILE
				telereport.setHoraInicio(telecarga.getOrderDate());
				telereport.setHoraFin(telecarga.getOrderDate());
				telereport.setVersion(telecarga.getVersionToDownload().toString());
				
				//MODIFICAR EL ESTATUS DE LA TELECARGA A FINALIZADA
				telecarga.setStatus("Finalizada");	
				ct.modifyTelecarga(telecarga);
				
				//REGISTRAMOS EN ACT EN TABLA TELEREPORT LA INFORMACION DE LA TELECARGA EXITOSA
				ctr.addTeleReport(telereport);
				mensaje = "Exitosa";
			}
			
		}
		catch(JDBCException e) {
			System.out.println("Sql State "+e.getSQLException().getSQLState());
			System.out.println("Eror Code "+e.getSQLException().getErrorCode());
			//UNIQUE CONSTRAINT ERROR CODE 1
			if (e.getSQLException().getErrorCode() == 1) {
				generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
			}
			//MANDATORIEDAD ERROR CODE 1400
			if (e.getSQLException().getErrorCode() == 1400) {
				generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
			}
			mensaje = "Error";
		}
        catch(Exception e){
            System.out.println("Error 505 MENSAJE	" +e.getMessage());
            System.out.println("Error 505 CAUSA		" +e.getCause());
            generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
            return "Error";
        }
		return mensaje;	
	}
	
    @POST
    @Path("/transaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaTransaction pruebaTransaccion(Transaction t) {

        RespuestaTransaction r = new RespuestaTransaction();
        TransactionDaoImp td = new TransactionDaoImp();
        Util util = new Util();       
        
        try 
        {
	        System.out.println("Transaction N "+ t.getSystems_trace_number());	
	        System.out.println("Nombre del afiliado: "+ t.getCard_acceptor_name());
	        System.out.println("Serial del terminal" + t.getCard_acceptor_terminal_id());
	        System.out.println("Iso: "+ t.getIso());
	        System.out.println("Pan: "+ t.getPan());
	        System.out.println("Modelo_del_terminal: "+ t.getModel());
	        System.out.println("Nombre_del_tarjetahabiente: "+ t.getCard_holder());
	        System.out.println("Emisor: "+ t.getIssuer());
	        System.out.println("Moneda"+ t.getCurrency());
	        System.out.println("Hora: "+ t.getTime_transaction());
	        System.out.println("Fecha: "+ t.getDate_transaction());
	        System.out.println("Fecha: "+ util.addDateFormat(t.getDate_transaction()));
	        System.out.println("Monto: "+ t.getAmount_transaction());
        
	        td.addTransaction(t);
	        System.out.println("Transaccion guardada");
	        r.setIso(t.getIso());
	        r.setStatus("1");
	        r.setMessage("Aprobado");
        
        }
        catch(JDBCException e) {
        	try {
        		System.out.println("Mensaje 502" +e.getMessage());
            	System.out.println("Causa 502" +e.getCause());
				System.out.println("Codigo de Error "+e.getSQLException().getSQLState());
				if (e.getSQLException().getSQLState().equals("22008")) {
					generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
					r.setIso(null);
		            r.setStatus("01861");
		            r.setMessage("literal does not match format string");
				}
        	}catch(Exception ex){
        		System.out.println("Mensaje 502" +e.getMessage());
            	System.out.println("Causa 502" +e.getCause());
        		r.setIso(null);
	            r.setStatus("502");
	            r.setMessage("No se logro escribir en bitacora");
        	}
        }
        catch(Exception e){
        	System.out.println("Mensaje" +e.getMessage());
        	System.out.println("Causa" +e.getCause());
        	generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());	
        	r.setIso(null);
            r.setStatus("503");
            r.setMessage("Error de Conexion");
        }
        return r;
    }
    
    @POST
    @Path("/addTransaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaTransaction Transaccion(Transaction2 t) {

        RespuestaTransaction r = new RespuestaTransaction();
        TransactionDao2 td = new TransactionDaoImp2();
        CardInfoDao ci = new CardInfoDaoImp();
        Util util = new Util();       
        
        try 
        {
	        System.out.println("Transaction N "+ t.getSystems_trace_number());	
	        System.out.println("Nombre del afiliado: "+ t.getCard_acceptor_name());
	        System.out.println("Serial del terminal" + t.getCard_acceptor_terminal_id());
	        System.out.println("Iso: "+ t.getIso());
	        System.out.println("Modelo_del_terminal: "+ t.getModel());
	        System.out.println("Moneda"+ t.getCurrency());
	        System.out.println("Hora: "+ t.getTime_transaction());
	        System.out.println("Fecha: "+ t.getDate_transaction());
	        System.out.println("Fecha: "+ util.addDateFormat(t.getDate_transaction()));
	        System.out.println("Monto: "+ t.getAmount_transaction());
	        System.out.println("Place: "+ t.getPlace());
	        if (t.getCard_info() == null || t.getCard_info().getId() == null) {
	        	r.setIso(null);
		        r.setStatus("0");
		        r.setMessage("Falta id de tarjeta");
	        }
	        else {
	        	System.out.println("CardInfo: "+ t.getCard_info().getId());
	        	CardInfo cardInfo = ci.findByID(t.getCard_info().getId());
	            
		        if (cardInfo != null)	{ 
		        	System.out.println("antes de guardar");
		        	td.addTransaction(t);
			        System.out.println("Transaccion guardada");
			        r.setIso(t.getIso());
			        r.setStatus("1");
			        r.setMessage("Transaccion Efectuada ");
		        }
		        else {
		        	r.setIso(null);
			        r.setStatus("0");
			        r.setMessage("Tarjeta no se Encuentra en Sistema");
		        }
	        }
        }
        catch(JDBCException e) {
        	try {
        		System.out.println("Mensaje 502" +e.getMessage());
            	System.out.println("Causa 502" +e.getCause());
				System.out.println("Codigo de Error "+e.getSQLException().getSQLState());
				if (e.getSQLException().getSQLState().equals("22008")) {
					generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());
					r.setIso(null);
		            r.setStatus("01861");
		            r.setMessage("literal does not match format string");
				}
        	}catch(Exception ex){
        		System.out.println("Mensaje 502" +e.getMessage());
            	System.out.println("Causa 502" +e.getCause());
        		r.setIso(null);
	            r.setStatus("502");
	            r.setMessage("No se logro escribir en bitacora");
        	}
        }
        catch(Exception e){
        	System.out.println("Mensaje" +e.getMessage());
        	System.out.println("Causa" +e.getCause());
        	generateBitacora("ST", "FAT", "LecBD ",e.getCause().toString());	
        	r.setIso(null);
            r.setStatus("503");
            r.setMessage("Error de Conexion");
        }
        
        return r;

    }
	
	/**
	 * METODO 
	 * @param mapParameter
	 * @param respuesta
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public Map<String, Object> getWrongResponse(Map<String, Object> mapParameter, RespuestaException respuesta, String errorCode, String message) {
		respuesta.setCodigoError(errorCode);
		respuesta.setMensaje(message);
	    mapParameter.put("Error", respuesta);
		return mapParameter;
	}
	
	public void generateBitacora(String ubi, String tip, String err, String message) {
		Util util = new Util();
        BitacoraErrorDao cBe = new BitacoraErrorDaoImp();
        BitacoraError be = new BitacoraError();		
        
        try {
	        //GENERA EL CODIGO DEL ERROR
	        String codigoError = "E_"+ubi+"_"+tip+"_"+err;
			//GUARDAR EN ARCHIVO TXT
			util.writeFile(util.StringTimeStampNow(), codigoError, message);
			//GUARDAR EN BD
			be.setCodigoError(codigoError);
			be.setMensajeError(message);
			be.setFecha(util.insertSqlTimeStamp());
			cBe.addBitacoraError(be);
        }
        catch(JDBCException e) {
			System.out.println("Sql State "+e.getSQLException().getSQLState());
    		System.out.println("Eror code "+e.getSQLException().getErrorCode());
    		//UNIQUE CONSTRAINT ERROR CODE 1
    		if (e.getSQLException().getErrorCode() == 1) {
    			System.out.println("Campo ya existe ");
    			System.out.println("Mensaje "+e.getMessage());
    			System.out.println("Causa   "+e.getCause());
    		}
    		//MANDATORIEDAD ERROR CODE 1400
    		if (e.getSQLException().getErrorCode() == 1400) {
    			System.out.println("Campo que no puede ser nulo ");
    			System.out.println("Mensaje "+e.getMessage());
    			System.out.println("Causa   "+e.getCause());
    		}
    		//MANDATORIEDAD CAMPO A MODIFICAR NO PUEDE SER NULO ERROR CODE 1407
    		if (e.getSQLException().getErrorCode() == 1407) {
    			System.out.println("Campo a modificar no puede ser nulo ");
    			System.out.println("Mensaje "+e.getMessage());
    			System.out.println("Causa   "+e.getCause());
    		}
    		//VALOR DE UN CAMPO EXCEDE EL LIMITE PERMITIDO EN BD
    		if (e.getSQLException().getErrorCode() == 12899) {
    			System.out.println("VALOR DE UN CAMPO EXCEDE EL LIMITE PERMITIDO EN BD");
    			System.out.println("Mensaje "+e.getMessage());
    			System.out.println("Causa   "+e.getCause());
    		}
    	}
		catch(Exception e) {
			System.out.println("Mensaje EX "+e.getMessage());
			System.out.println("Causa 	EX "+e.getCause());
		}
	}
	
	
	/**
	 * METODO QUE ASIGNA TODA LA INFORMACION DE LOS PARAMETROS DE TELECARGA ACT EN ST
	 * @param telecargaACT
	 * @param telecargaST
	 */
	public void addTelecargaACTinST(TelecargaACT telecargaACT, TelecargaST telecargaST) {
		telecargaST.setRqtKey(telecargaACT.getRqtKey());
		telecargaST.setBnkKey(telecargaACT.getBnkKey());
		telecargaST.setBnkName(telecargaACT.getBnkName());
		telecargaST.setTerId(telecargaACT.getTerId());
		telecargaST.setAppDesc(telecargaACT.getAppDesc());
		telecargaST.setPlanDesc(telecargaACT.getPlanDesc());
		telecargaST.setOperativeTer(telecargaACT.getOperativeTer());
		telecargaST.setForceSaleTer(telecargaACT.getForceSaleTer());
		telecargaST.setMembershipComm(telecargaACT.getMembershipComm());
		telecargaST.setCommerceName(telecargaACT.getCommerceName());
		telecargaST.setCommerceAddres(telecargaACT.getCommerceAddres());
		telecargaST.setPopulaitonName(telecargaACT.getPopulaitonName());
		telecargaST.setOrderDate(telecargaACT.getOrderDate());
		telecargaST.setTerminalMark(telecargaACT.getTerminalMark());
		telecargaST.setTerminalModel(telecargaACT.getTerminalModel());
		telecargaST.setEncrTermId(telecargaACT.getEncrTermId());
		telecargaST.setTcFolio(telecargaACT.getTcFolio());
		telecargaST.setPaperKey(telecargaACT.getPaperKey());
		telecargaST.setZipCode(telecargaACT.getZipCode());
		telecargaST.setAllowPreGratif(telecargaACT.getAllowPreGratif());
		telecargaST.setAllowPostGratif(telecargaACT.getAllowPostGratif());
		telecargaST.setAllowCashback(telecargaACT.getAllowCashback());
		telecargaST.setReturnLimit(telecargaACT.getReturnLimit());
		telecargaST.setRegistryVersion(telecargaACT.getRegistryVersion());
		telecargaST.setMacFlag(telecargaACT.getMacFlag());
		telecargaST.setAuthPhoneNumber(telecargaACT.getAuthPhoneNumber());
		telecargaST.setAvantelPhoneNumber(telecargaACT.getAvantelPhoneNumber());
		telecargaST.setTelmexPhoneNumber(telecargaACT.getTelmexPhoneNumber());
		telecargaST.setTcDaysCount(telecargaACT.getTcDaysCount());
		telecargaST.setServiceCarCom(telecargaACT.getServiceCarCom());
		telecargaST.setComIva(telecargaACT.getComIva());
		telecargaST.setComMultimerchant(telecargaACT.getComMultimerchant());
		telecargaST.setReturnKey(telecargaACT.getReturnKey());
		telecargaST.setGirKey(telecargaACT.getGirKey());
		telecargaST.setSicKey(telecargaACT.getSicKey());
		telecargaST.setPaysheetApp(telecargaACT.getPaysheetApp());
		telecargaST.setAmexApp(telecargaACT.getAmexApp());
		telecargaST.setDinersApp(telecargaACT.getDinersApp());
		telecargaST.setServicomId(telecargaACT.getServicomId());
		telecargaST.setTravelAgent(telecargaACT.getTravelAgent());
		telecargaST.seteCommerceId(telecargaACT.geteCommerceId());
		telecargaST.setCarrierKey(telecargaACT.getCarrierKey());
		telecargaST.setTerminalUser(telecargaACT.getTerminalUser());
		telecargaST.setTerminalPass(telecargaACT.getTerminalPass());
		telecargaST.setTerminalId(telecargaACT.getTerminalId());
		telecargaST.setServiceType(telecargaACT.getServiceType());
		telecargaST.setPhoneCarrier1(telecargaACT.getPhoneCarrier1());
		telecargaST.setPhoneCarrier2(telecargaACT.getPhoneCarrier2());
		telecargaST.setPhoneCarrier3(telecargaACT.getPhoneCarrier3());
		telecargaST.setKeybnk(telecargaACT.getKeybnk());
		telecargaST.setEsn(telecargaACT.getEsn());
		telecargaST.setIp(telecargaACT.getIp());
		telecargaST.setPort(telecargaACT.getPort());
		telecargaST.setServicommerceCount(telecargaACT.getServicommerceCount());
		telecargaST.setCountry(telecargaACT.getCountry());
		telecargaST.setCurrency(telecargaACT.getCurrency());
		telecargaST.setPromotions(telecargaACT.getPromotions());
		telecargaST.setQpsMaxAmount(telecargaACT.getQpsMaxAmount());
		telecargaST.setQpsPrintLegend(telecargaACT.getQpsPrintLegend());
		telecargaST.setStatus("Sin Finalizar");
	}
	
	public void addBduACTinST(BduACT bduACT, Bdu bduST) {
		bduST.setMembershipComm(bduACT.getMembershipComm());
		bduST.setCommerceName(bduACT.getCommerceName());
		bduST.setTcFolio(bduACT.getTcFolio());
		bduST.setTerId(bduACT.getTerId());
		bduST.setOperativeTer(bduACT.getOperativeTer());
		bduST.setForceSaleTer(bduACT.getForceSaleTer());
		bduST.setEncrTermId(bduACT.getEncrTermId());
		bduST.setAllowPreGratif(bduACT.getAllowPreGratif());
		bduST.setAllowPostGratif(bduACT.getAllowPostGratif());
		bduST.setAllowCashback(bduACT.getAllowCashback());
		bduST.setReturnLimit(bduACT.getReturnLimit());
		bduST.setMacFlag(bduACT.getMacFlag());
		bduST.setAppDesc(bduACT.getAppDesc());
		bduST.setBajaLogica(bduACT.getBajaLogica());
		bduST.setCurrency(bduACT.getCurrency());
	}
	
}