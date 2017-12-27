package sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BDU_MULT")
public class BduACT implements Serializable {

	
	private static final long serialVersionUID = -8439176865751764993L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementoBduMult")
	@SequenceGenerator(
	    name="incrementoBduMult",
		sequenceName="idBduACT_seq",
		allocationSize=1)
    @Column(name = "id")
	private int 	id;		
	@Column(name = "comAfiliación")
	private Integer membershipComm;		 	//Número de afiliación del comercio
	@Column(name = "comNombre")
	private String	commerceName;           //Nombre del comercio
	@Column(name = "odtFolioTelecarga")
	private Integer tcFolio;				//Folio de telecarga
	@Column(name = "terId")
	private String 	terId;					//Numero de la caja de la terminal
	@Column(name = "terOperativa")
	private char 	operativeTer; 			//M = operativa manual (digitar no. De tarjeta )	o N =operativa normal (tarjeta deslizada)
	@Column(name = "terVentaForzada")
	private Integer forceSaleTer;  			//0= no lleva venta forzada ó 1 = Lleva venta forzada.
	@Column(name = "terIdEncr")
	private String 	encrTermId;				//Valor del numero de terminal Id con el cual se deberá de conectar a base 24
	@Column(name = "terPrepropina")
	private Integer allowPreGratif;			//0 = no permite prepropina 1= permite prepropina
	@Column(name = "terPostpropina")
	private Integer allowPostGratif;		//0 = no permite pospropina 1 = permite pospropina
	@Column(name = "terCashback")
	private Integer allowCashback;			//0 = no permite cash back, 1 permite cash back
	@Column(name = "terLimiteDevolucion")
	private Integer returnLimit;			//Indica el limite de devolución, aunque este valor es informativo, ya que el limite lo valida el host.
	@Column(name = "terMacFlag")
	private Integer macFlag;				//informativo
	@Column(name = "aplDescripción")
	private String  appDesc;				//Descripción de la aplicación (comercio, restaurante, sucursal)
	@Column(name = "terBajaLogica")			
	private Integer bajaLogica;				//Valor de baja logica, los valores pueden ser:0 inactiva y 1 activa no utilizado
	@Column(name = "terDolar")		
	private Integer currency;				//Muestra si la afiliación esta asociada a pesos (0) o dólares (1)
	
	
	public BduACT() {

	}
	
	public BduACT(int id, Integer membershipComm, String commerceName, Integer tcFolio, String terId, char operativeTer,
			Integer forceSaleTer, String encrTermId, Integer allowPreGratif, Integer allowPostGratif,
			Integer allowCashback, Integer returnLimit, Integer macFlag, String appDesc, Integer bajaLogica,
			Integer currency) {
		super();
		this.id = id;
		this.membershipComm = membershipComm;
		this.commerceName = commerceName;
		this.tcFolio = tcFolio;
		this.terId = terId;
		this.operativeTer = operativeTer;
		this.forceSaleTer = forceSaleTer;
		this.encrTermId = encrTermId;
		this.allowPreGratif = allowPreGratif;
		this.allowPostGratif = allowPostGratif;
		this.allowCashback = allowCashback;
		this.returnLimit = returnLimit;
		this.macFlag = macFlag;
		this.appDesc = appDesc;
		this.bajaLogica = bajaLogica;
		this.currency = currency;
	}
	
	//BORRAR
	public BduACT(int id, Integer membershipComm, String commerceName, Integer tcFolio) {
		super();
		this.id = id;
		this.membershipComm = membershipComm;
		this.commerceName = commerceName;
		this.tcFolio = tcFolio;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Integer getTcFolio() {
		return tcFolio;
	}
	public void setTcFolio(Integer tcFolio) {
		this.tcFolio = tcFolio;
	}
	public String getTerId() {
		return terId;
	}
	public void setTerId(String terId) {
		this.terId = terId;
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
	public String getEncrTermId() {
		return encrTermId;
	}
	public void setEncrTermId(String encrTermId) {
		this.encrTermId = encrTermId;
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
	public Integer getMacFlag() {
		return macFlag;
	}
	public void setMacFlag(Integer macFlag) {
		this.macFlag = macFlag;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public Integer getBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(Integer bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "Bdu [id=" + id + ", membershipComm=" + membershipComm + ", commerceName=" + commerceName + ", tcFolio="
				+ tcFolio + ", terId=" + terId + ", operativeTer=" + operativeTer + ", forceSaleTer=" + forceSaleTer
				+ ", encrTermId=" + encrTermId + ", allowPreGratif=" + allowPreGratif + ", allowPostGratif="
				+ allowPostGratif + ", allowCashback=" + allowCashback + ", returnLimit=" + returnLimit + ", macFlag="
				+ macFlag + ", appDesc=" + appDesc + ", bajaLogica=" + bajaLogica + ", currency=" + currency + "]";
	}


}


