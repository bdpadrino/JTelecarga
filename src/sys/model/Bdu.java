package sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bdu")
public class Bdu {

	@Id
    @Column(name = "id")
	private int 	id;		
	@Column(name = "comAfiliaci�n")
	private Integer membershipComm;		 	//N�mero de afiliaci�n del comercio
	@Column(name = "comNombre")
	private String	commerceName;           //Nombre del comercio
	@Column(name = "odtFolioTelecarga")
	private Integer tcFolio;				//Folio de telecarga
	@Column(name = "terId")
	private Integer terId;					//Numero de la caja de la terminal
	@Column(name = "terOperativa")
	private char 	operativeTer; 			//M = operativa manual (digitar no. De tarjeta )	o N =operativa normal (tarjeta deslizada)
	@Column(name = "terVentaForzada")
	private Integer forceSaleTer;  			//0= no lleva venta forzada � 1 = Lleva venta forzada.
	@Column(name = "terIdEncr")
	private String 	encrTermId;				//Valor del numero de terminal Id con el cual se deber� de conectar a base 24
	@Column(name = "terPrepropina")
	private Integer allowPreGratif;			//0 = no permite prepropina 1= permite prepropina
	@Column(name = "terPostpropina")
	private Integer allowPostGratif;		//0 = no permite pospropina 1 = permite pospropina
	@Column(name = "terCashback")
	private Integer allowCashback;			//0 = no permite cash back, 1 permite cash back
	@Column(name = "terLimiteDevolucion")
	private Integer returnLimit;			//Indica el limite de devoluci�n, aunque este valor es informativo, ya que el limite lo valida el host.
	@Column(name = "terMacFlag")
	private Integer macFlag;				//informativo
	@Column(name = "aplDescripci�n")
	private String  appDesc;				//Descripci�n de la aplicaci�n (comercio, restaurante, sucursal)
	@Column(name = "terBajaLogica")			
	private Integer bajaLogica;				//Valor de baja logica, los valores pueden ser:0 inactiva y 1 activa no utilizado
	@Column(name = "terDolar")		
	private Integer currency;				//Muestra si la afiliaci�n esta asociada a pesos (0) o d�lares (1)
}
