package sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class Telecarga {

	@Id
    @Column(name = "solCve")
	private Integer rqtKey;  				//Clave de solicitud, (no se usa)
	@Column(name = "bcoCve")
	private Integer bnkKey;					//Clave del banco
	@Column(name = "bcoNombre")
	private String 	bnkName;				//Nombre del banco, para la impresión en pagaré
	@Column(name = "terId")
	private Integer terId;					//Numero de la caja de la terminal
	@Column(name = "aplDescripción")
	private String  appDesc;				//Descripción de la aplicación (comercio, restaurantesucursal)
	@Column(name = "plnDescripción")
	private String  planDesc;				//Descripción del plan, crédito o débito (solamente paratransacciones con pin-pad)
	@Column(name = "terOperativa")
	private char 	operativeTer; 			//	M = operativa manual (digitar no. De tarjeta )	o N =operativa normal (tarjeta deslizada)
	@Column(name = "terVentaForzada")
	private Integer forceSaleTer;  			//0= no lleva venta forzada ó 1 = Lleva venta forzada.
	@Column(name = "comAfiliación")
	private Integer membershipComm;		 	//Número de afiliación del comercio
	@Column(name = "comNombre")
	private String	commerceName;			//Nombre del comercio
	@Column(name = "comDomicilio")
	private String 	commerceAddres;			//Domicilio del comercio
	@Column(name = "pbiNombre")
	private String	populaitonName;			//Población del comercio
	@Column(name = "fechaOdt")
	private Date	orderDate;				//Fecha de captura de la orden de trabajo
	@Column(name = "modMarca")
	private String 	terminalMark;			//Marca de terminal
	@Column(name = "modModelo")
	private String 	terminalModel;			//Modelo de terminal
	@Column(name = "terIdEncr")
	private String 	encrTermId;				//Valor del numero de terminal Id con el cual se deberá de conectar a base 24
	@Column(name = "odtFolioTelecarga")
	private Integer tcFolio;					//Folio de telecarga
	@Column(name = "cTram")
	private String	paperKey;				//Clave de tramite, actualmente no es utilizado.
	@Column(name = "cpCve")
	private Integer zipCode;				//Código postal (valor informativo)
	@Column(name = "terPrepropina")
	private Integer allowPreGratif;			//0 = no permite prepropina 1= permite prepropina
	@Column(name = "terPostpropina")
	private Integer allowPostGratif;		//0 = no permite pospropina 1 = permite pospropina
	@Column(name = "terCashback")
	private Integer allowCashback;			//0 = no permite cash back, 1 permite cash back
	@Column(name = "terLimiteDevolucion")
	private Integer returnLimit;			//Indica el limite de devolución, aunque este valor es informativo, ya que el limite lo valida el host.
	@Column(name = "terVersion")
	private Integer registryVersion;		//Versión del registro en la base de datos
	@Column(name = "terMacFlag")
	private Integer macFlag;				//informativo
	@Column(name = "telLocal")
	private String 	authPhoneNumber;		//Teléfono local para solicitar autorización
	@Column(name = "telAvantel")
	private String 	avantelPhoneNumber;		//Teléfono secundario para solicitar autorización.
	@Column(name = "telTelmex")
	private String 	telmexPhoneNumber;		//Teléfono terciario para solicitar autorización.
	@Column(name = "numTelecargaDias")
	private Integer tcDaysCount;			//Número de días en los que la POS realizará la petición de telecarga.
	@Column(name = "comCarServicio")
	private Integer serviceCarCom;			//Comisión para aplicaciones de gasolinera para aplicar sobre el monto total del consumo
	@Column(name = "comIva")
	private Integer comIva;					//Solo para aplicaciones de gasolinera
	@Column(name = "comMultimerchant")
	private Integer comMultimerchant;		//Indica Si	 el folio pertenece a una aplicación multimerchant o servicomercios
	@Column(name = "devCve")
	private Integer returnKey;				//Clave de aceptación de devolución 01 = venta original dentro del batch, 02= sin restricción de batch, 3 = no permite devolución
	@Column(name = "girCve")
	private Integer girKey;					//2560, se deberá de validar que cuando el folio tenga el valor de 2560 ponga la leyenda en el pagare de Venta Telefónica
	@Column(name = "sicCve")
	private Integer sicKey;					//5969, se deberá de validar que cuando tenga el valor de 5969 ponga la leyenda en el pagare de venta telefónica, el Campo de Sic_Cle es el valido para esta información el GIR_CVE es informativo.
	@Column(name = "aplNomina")
	private Integer paysheetApp;			//Aplicación exclusiva
	@Column(name = "aplAmex")
	private Integer amexApp;				//Aplicación exclusiva
	@Column(name = "aplDiners")
	private Integer dinersApp;				//Aplicación exclusiva
	@Column(name = "comServicom")
	private Integer servicomId;				//identificador de comercios con aplicación de servicomercios, valor de 2 = aplicación de servicomercios, 1 = en proceso de actualización, 0 = no servicomercios 3 = aplicación con multiaplicativo
	@Column(name = "ageViajes")
	private Integer travelAgent;
	@Column(name = "comElectronico")
	private Integer eCommerceId;			//Identificador de comercios con indicadores, 1= comercio electrónico, 0 o nulo = no comercio electrónico.
	@Column(name = "cveCarrier")
	private Integer carrierKey;				//Define el valor con el cual se encuentra en el catalogo de BDU
	@Column(name = "terUser")
	private String  terminalUser;			//Establece el usuario con el cual iniciara sesión con el proveedor de celular
	@Column(name = "terPassword")
	private String  terminalPass;			//Establece el password con el cual iniciara sesión con el proveedor de celular
	@Column(name = "idTerminal")
	private String  terminalId;				//Solo aplica para la telefonía CDMA
	@Column(name = "terProtocolo")
	private Integer serviceType;			//Define el tipon de servicio que presta cada proveedor de celular. Numero telefónico al cual la terminal realizara la llamada dependiendo del proveedor telefónico de telefonía celular: Para IUSACELL #777
	@Column(name = "telCarrier1")
	private String  phoneCarrier1;			//Para TELCEL *99***1# Para Movistar *99***1# Numero telefónico al cual la terminal realizara la llamada dependiendo del proveedor telefónico de telefonía celular: Para IUSACELL #777
	@Column(name = "telCarrier2")
	private String  phoneCarrier2;			//Para TELCEL *99***1# Para Movistar *99***1# Numero telefónico al cual la terminal realizara la llamada dependiendo del proveedor telefónico de telefonía celular: Para IUSACELL #777
	@Column(name = "telCarrier3")
	private String  phoneCarrier3;			//Para TELCEL *99***1# Para Movistar *99***1#
	@Column(name = "llaveBanco")
	private String  keybnk;					//Se asigna el APN correspondiente para cada proveedor de celular.
	@Column(name = "esn")
	private String  esn;					//Es el numero de serie de la terminal Asigna la IP al ambiente donde se realizaran las pruebas. Producción: 140.240.11.68
	@Column(name = "ip")
	private String  ip;						//Desarrollo: 140.240.11.202 Se asigna conforme al ambiente que se este utilizando para la realización de pruebas. Producción: 1530
	@Column(name = "puerto")
	private Integer port;					//Desarrollo: 1515
	@Column(name = "countServicomercios")
	private Integer servicommerceCount;		//Cuenta los comercios que estan asociados
	@Column(name = "pais")
	private Integer country;				//Pais residente de la terminal
	@Column(name = "moneda")
	private Integer currency;				//0= Pesos Mexicanos 1= Dolares
	@Column(name = "bcoNpromocionesombre")
	private String	promotions;				//Promociones validas para la terminal
	@Column(name = "montoMaxOper")
	private String 	qpsMaxAmount;			//Monto maximo permitido para transacciones QPS
	@Column(name = "leyendaImpresión")
	private String  qpsPrintLegend;			//Leyenda de impresión para vouchers QPS
	
	
	public Integer getRqtKey() {
		return rqtKey;
	}
	public void setRqtKey(Integer rqtKey) {
		this.rqtKey = rqtKey;
	}
	public Integer getBnkKey() {
		return bnkKey;
	}
	public void setBnkKey(Integer bnkKey) {
		this.bnkKey = bnkKey;
	}
	public String getBnkName() {
		return bnkName;
	}
	public void setBnkName(String bnkName) {
		this.bnkName = bnkName;
	}
	public Integer getTerId() {
		return terId;
	}
	public void setTerId(Integer terId) {
		this.terId = terId;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public String getPlanDesc() {
		return planDesc;
	}
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}
	public char getOperativeTer() {
		return operativeTer;
	}
	public void setOperativeTer(char operativeTer) {
		this.operativeTer = operativeTer;
	}
	public Integer getForceSaleTer() {
		return forceSaleTer;
	}
	public void setForceSaleTer(Integer forceSaleTer) {
		this.forceSaleTer = forceSaleTer;
	}
	public Integer getMembershipComm() {
		return membershipComm;
	}
	public void setMembershipComm(Integer membershipComm) {
		this.membershipComm = membershipComm;
	}
	public String getCommerceName() {
		return commerceName;
	}
	public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	public String getCommerceAddres() {
		return commerceAddres;
	}
	public void setCommerceAddres(String commerceAddres) {
		this.commerceAddres = commerceAddres;
	}
	public String getPopulaitonName() {
		return populaitonName;
	}
	public void setPopulaitonName(String populaitonName) {
		this.populaitonName = populaitonName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getTerminalMark() {
		return terminalMark;
	}
	public void setTerminalMark(String terminalMark) {
		this.terminalMark = terminalMark;
	}
	public String getTerminalModel() {
		return terminalModel;
	}
	public void setTerminalModel(String terminalModel) {
		this.terminalModel = terminalModel;
	}
	public String getEncrTermId() {
		return encrTermId;
	}
	public void setEncrTermId(String encrTermId) {
		this.encrTermId = encrTermId;
	}
	public Integer getTcFolio() {
		return tcFolio;
	}
	public void setTcFolio(Integer tcFolio) {
		this.tcFolio = tcFolio;
	}
	public String getPaperKey() {
		return paperKey;
	}
	public void setPaperKey(String paperKey) {
		this.paperKey = paperKey;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public Integer getAllowPreGratif() {
		return allowPreGratif;
	}
	public void setAllowPreGratif(Integer allowPreGratif) {
		this.allowPreGratif = allowPreGratif;
	}
	public Integer getAllowPostGratif() {
		return allowPostGratif;
	}
	public void setAllowPostGratif(Integer allowPostGratif) {
		this.allowPostGratif = allowPostGratif;
	}
	public Integer getAllowCashback() {
		return allowCashback;
	}
	public void setAllowCashback(Integer allowCashback) {
		this.allowCashback = allowCashback;
	}
	public Integer getReturnLimit() {
		return returnLimit;
	}
	public void setReturnLimit(Integer returnLimit) {
		this.returnLimit = returnLimit;
	}
	public Integer getRegistryVersion() {
		return registryVersion;
	}
	public void setRegistryVersion(Integer registryVersion) {
		this.registryVersion = registryVersion;
	}
	public Integer getMacFlag() {
		return macFlag;
	}
	public void setMacFlag(Integer macFlag) {
		this.macFlag = macFlag;
	}
	public String getAuthPhoneNumber() {
		return authPhoneNumber;
	}
	public void setAuthPhoneNumber(String authPhoneNumber) {
		this.authPhoneNumber = authPhoneNumber;
	}
	public String getAvantelPhoneNumber() {
		return avantelPhoneNumber;
	}
	public void setAvantelPhoneNumber(String avantelPhoneNumber) {
		this.avantelPhoneNumber = avantelPhoneNumber;
	}
	public String getTelmexPhoneNumber() {
		return telmexPhoneNumber;
	}
	public void setTelmexPhoneNumber(String telmexPhoneNumber) {
		this.telmexPhoneNumber = telmexPhoneNumber;
	}
	public Integer getTcDaysCount() {
		return tcDaysCount;
	}
	public void setTcDaysCount(Integer tcDaysCount) {
		this.tcDaysCount = tcDaysCount;
	}
	public Integer getServiceCarCom() {
		return serviceCarCom;
	}
	public void setServiceCarCom(Integer serviceCarCom) {
		this.serviceCarCom = serviceCarCom;
	}
	public Integer getComIva() {
		return comIva;
	}
	public void setComIva(Integer comIva) {
		this.comIva = comIva;
	}
	public Integer getComMultimerchant() {
		return comMultimerchant;
	}
	public void setComMultimerchant(Integer comMultimerchant) {
		this.comMultimerchant = comMultimerchant;
	}
	public Integer getReturnKey() {
		return returnKey;
	}
	public void setReturnKey(Integer returnKey) {
		this.returnKey = returnKey;
	}
	public Integer getGirKey() {
		return girKey;
	}
	public void setGirKey(Integer girKey) {
		this.girKey = girKey;
	}
	public Integer getSicKey() {
		return sicKey;
	}
	public void setSicKey(Integer sicKey) {
		this.sicKey = sicKey;
	}
	public Integer getPaysheetApp() {
		return paysheetApp;
	}
	public void setPaysheetApp(Integer paysheetApp) {
		this.paysheetApp = paysheetApp;
	}
	public Integer getAmexApp() {
		return amexApp;
	}
	public void setAmexApp(Integer amexApp) {
		this.amexApp = amexApp;
	}
	public Integer getDinersApp() {
		return dinersApp;
	}
	public void setDinersApp(Integer dinersApp) {
		this.dinersApp = dinersApp;
	}
	public Integer getServicomId() {
		return servicomId;
	}
	public void setServicomId(Integer servicomId) {
		this.servicomId = servicomId;
	}
	public Integer getTravelAgent() {
		return travelAgent;
	}
	public void setTravelAgent(Integer travelAgent) {
		this.travelAgent = travelAgent;
	}
	public Integer geteCommerceId() {
		return eCommerceId;
	}
	public void seteCommerceId(Integer eCommerceId) {
		this.eCommerceId = eCommerceId;
	}
	public Integer getCarrierKey() {
		return carrierKey;
	}
	public void setCarrierKey(Integer carrierKey) {
		this.carrierKey = carrierKey;
	}
	public String getTerminalUser() {
		return terminalUser;
	}
	public void setTerminalUser(String terminalUser) {
		this.terminalUser = terminalUser;
	}
	public String getTerminalPass() {
		return terminalPass;
	}
	public void setTerminalPass(String terminalPass) {
		this.terminalPass = terminalPass;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public String getPhoneCarrier1() {
		return phoneCarrier1;
	}
	public void setPhoneCarrier1(String phoneCarrier1) {
		this.phoneCarrier1 = phoneCarrier1;
	}
	public String getPhoneCarrier2() {
		return phoneCarrier2;
	}
	public void setPhoneCarrier2(String phoneCarrier2) {
		this.phoneCarrier2 = phoneCarrier2;
	}
	public String getPhoneCarrier3() {
		return phoneCarrier3;
	}
	public void setPhoneCarrier3(String phoneCarrier3) {
		this.phoneCarrier3 = phoneCarrier3;
	}
	public String getKeybnk() {
		return keybnk;
	}
	public void setKeybnk(String keybnk) {
		this.keybnk = keybnk;
	}
	public String getEsn() {
		return esn;
	}
	public void setEsn(String esn) {
		this.esn = esn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getServicommerceCount() {
		return servicommerceCount;
	}
	public void setServicommerceCount(Integer servicommerceCount) {
		this.servicommerceCount = servicommerceCount;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public String getPromotions() {
		return promotions;
	}
	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}
	public String getQpsMaxAmount() {
		return qpsMaxAmount;
	}
	public void setQpsMaxAmount(String qpsMaxAmount) {
		this.qpsMaxAmount = qpsMaxAmount;
	}
	public String getQpsPrintLegend() {
		return qpsPrintLegend;
	}
	public void setQpsPrintLegend(String qpsPrintLegend) {
		this.qpsPrintLegend = qpsPrintLegend;
	}

	
	
}
