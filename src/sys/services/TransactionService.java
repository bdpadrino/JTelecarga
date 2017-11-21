package sys.services;

//import java.io.FileNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.JDBCException;

import sys.dao.imp.*;
import sys.model.RespuestaTransaction;
import sys.model.Transaction;
import sys.util.CustomException;
import sys.util.Util;
import sys.model.TelecargaACT;
import sys.model.TelecargaST;
import sys.model.TerminalInfo;
import sys.model.BitacoraError;

@Path("/prosa")
public class TransactionService {

    @POST
    @Path("/payment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaTransaction pruebaJson(Transaction t) {

        RespuestaTransaction r = new RespuestaTransaction();
        TransactionDaoImp td = new TransactionDaoImp();
        	       
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
	        System.out.println("Monto: "+ t.getAmount_transaction());
        
	        td.addTransaction(t);
	        System.out.println("Transaccion guardada");
	        r.setIso(t.getIso());
	        r.setStatus("OK");
	        r.setMessage("Aprobado");
        
        }
        catch(Exception e){
        	System.out.println("Monto:"+ t.getAmount_transaction());
        	r.setIso("");
            r.setStatus("NO OK");
            r.setMessage("Error de Conexion");
        }
        
        return r;

    }
    TelecargaACT telecargaACT = new TelecargaACT();
    TelecargaST telecargaST = new TelecargaST();

	@POST
    @Path("/telecarga")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TelecargaST pruebaTelecarga(TerminalInfo stEntrante) {

        TelecargaACT telecargaACT = new TelecargaACT();
        TelecargaST telecargaST = new TelecargaST();
        TelecargaSTDaoImp cST = new TelecargaSTDaoImp();  
        TelecargaACTDaoImp cACT = new TelecargaACTDaoImp();  
        TerminalInfo ti = new TerminalInfo();
        TerminalInfoDaoImp cti = new TerminalInfoDaoImp();
        //TerminalVersion tv = new TerminalVersion();
        TerminalVersionDaoImp ctv = new TerminalVersionDaoImp();
        BitacoraError be = new BitacoraError();
        CustomException cException = new CustomException();
        Util util = new Util();
        	       
        try 
        {
        	System.out.println("Solicitud de Telecarga Recibida");
	        System.out.println("Folio Recibido:  "+stEntrante.getNumeroFolio());	
	
	        //BUSCAR EN EL ST FOLIO DEL TERMINAL A ACTUALIZAR
	        ti = cti.findTerminalInfoByFolio(stEntrante.getNumeroFolio());	        
	        if (ti == null)
				System.out.println("Folio en terminal info: " + stEntrante.getNumeroFolio() + "No se encuentra Registrado en ST");
	        else {
	        	
		        //BUSCAR EN ACT LA INFORMACION SEGUN FOLIO	        
		        telecargaACT = cACT.findTelecargaByFolio(ti.getNumeroFolio()); 
		        if (telecargaACT != null){
			        System.out.println("Folio recibida de ACT "+telecargaACT.getTcFolio());
			        //System.out.println("Terminal recibida de ACT "+telecargaACT.getTerId());
			        
			        //VALIDAR INFORMACION DE ACT CON LA DEL ST
			        System.out.println("AL VALIDAR folio en ACT "+telecargaACT.getTcFolio() +" folio en ST "+ ti.getNumeroFolio());
			      
			        if(telecargaACT.getTcFolio().equals(ti.getNumeroFolio())) {
			        	System.out.println("igual el folio");
			        	if(telecargaACT.getTerminalMark().trim().equals(ti.getMarca().trim())) {
			        		System.out.println("igual la marca");
			        		if(ti.getModelo().trim().equals(telecargaACT.getTerminalModel().trim())) {
			        			System.out.println("igual el modelo");
					        	System.out.println("La informacion ha sido validada correctamente");
					
					        	//BUSCA LA ULTIMA VERSION DE UNA APLICACION SEGUN MODELO Y TIPO APP
					        	Double version = ctv.findLastVersionOfModel(stEntrante.getModelo(), stEntrante.getTipo_aplicacion());
					        	System.out.println("Ultima version disponible en ST para este modelo y tipo de app"+version+" version actual en POS "+stEntrante.getVersion());
					        	telecargaST.setUpdateApp(false);
					        	if (version > ti.getVersion() ) {
					        		System.out.println("Se debe actualizar la app");
					        		telecargaST.setUpdateApp(true);
					        	}
					        	else {
					        		System.out.println(" la app ya esta actualizada version" +version);
					        		telecargaST.setUpdateApp(false);
				        		}
					        	
					        	//SE REGISTRA LA INFORMACION DE ACT EN ST
					        	addTelecargaACTinST(telecargaACT, telecargaST);
					        	System.out.println("AGREGADO EN ST "+telecargaST.toString());					        	
					        	cST.addTelecarga(telecargaST);
					        	
			        		}
			        		else {
			        			//ERROR INFORMATIVO LOS MODELOS NO SON IGUALES
			        			System.out.println("ERROR INFORMATIVO LOS MODELOS NO SON IGUALES");
			        			util.writeFile(be.createErrorCode("ST", "INF", "NoVal"), "LOS MODELOS NO SON IGUALES");
			        		}
			        	}
			        	else {
			        		//ERROR INFORMATIVO LAS MARCAS NO SON IGUALES
			        		System.out.println("ERROR INFORMATIVO LAS MARCAS NO SON IGUALES");
			        		util.writeFile(be.createErrorCode("ST", "INF", "NoVal"), "LAS MARCAS NO SON IGUALES");
			        	}			        		
			        }
			        else {
			        	//ERROR INFORMATIVO FOLIO RECIBIDO DE POS NO SE ENCUENTRA EN ST
			        	System.out.println("ERROR INFORMATIVO FOLIO RECIBIDO DE POS " +ti.getNumeroFolio()+" NO SE ENCUENTRA EN ST");
			        	util.writeFile(be.createErrorCode("ST", "INF", "NoVal"), "FOLIO RECIBIDO DE POS NO SE ENCUENTRA EN ACT");
			        }
		        }
		        else {
		        	System.out.println("folio: "+ti.getNumeroFolio()+ " No encontrado en ACT ");	
			      	//ERROR INFORMATIVO FOLIO RECIBIDO DE POS NO SE ENCUENTRA EN ACT
		        	System.out.println("ERROR INFORMATIVO FOLIO RECIBIDO DE POS " +ti.getNumeroFolio()+" NO SE ENCUENTRA EN ACT");
		        	util.writeFile(be.createErrorCode("ST", "INF", "NoVal"), "FOLIO RECIBIDO DE POS NO SE ENCUENTRA EN ACT");
		        }
	        }	        
	        		
        }
        catch(JDBCException e){
        	System.out.println("error jdbc");
           	cException.CustomException("pruebina", e);
           	System.out.println("ERROR FATAL FOLIO RECIBIDO DE POS " +ti.getNumeroFolio()+" NO SE ENCUENTRA EN ACT");
        	util.writeFile(be.createErrorCode("ST", "FAT", "LecBD"), "Error al leer la BD ");
        }
        /*catch(FileNotFoundException e){
        	cException.CustomException("pruebina", e);
        }
        catch(IOException e){
        	cException.CustomException("pruebina", e);
        }*/
        catch(Exception e){
            System.out.println("Error 502 MENSAJE	" +e.getMessage());
            System.out.println("Error 502 CAUSA		" +e.getCause());
            e.printStackTrace();
            //REGISTRAR EN BITACORA EL ERROR
            
        }
        //SE DEVUELVE LA INFORMACION DEL ACT
        return telecargaST;

    }
	
	/**
	 * METODO QUE ASIGNA TODA LA INFORMACION DE LOS PARAMETROS DE TELECARGA ACT EN ST
	 * @param telecargaACT
	 * @param telecargaST
	 */
	public void addTelecargaACTinST(TelecargaACT telecargaACT, TelecargaST telecargaST) {
		telecargaST.setRqtKey(1010/*telecargaACT.getRqtKey()*/);
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
	}
	
}