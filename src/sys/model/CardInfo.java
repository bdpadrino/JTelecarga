package sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CARD_INFO")
public class CardInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementoIdCard_Info")
	@SequenceGenerator(
	    name="incrementoIdCard_Info",
		sequenceName="CARD_INFO_id",
		allocationSize=1)
	@Column(name = "id")													 //ID DE LA TRANSACCION
	private Integer id;
	@Column(name = "pan")
	private String  pan;													 //NUMERO DE LA TARJETA DE DEBITO
	@Column(name = "card_holder")
	private String  card_holder;                                             //NOMBRE TEJETAHABIENTE
	@Column(name = "issuer")
	private String  issuer;                                                  //EMISOR "VISA"
	@Column(name = "date_expiration")										
	private Date	date_expiration;  										 //FECHA DE EXPIRACION DE LA TARJETA
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public Date getDate_expiration() {
		return date_expiration;
	}
	public void setDate_expiration(Date date_expiration) {
		this.date_expiration = date_expiration;
	}
	
	@Override
	public String toString() {
		return "Card_Info [id=" + id + ", pan=" + pan + ", card_holder=" + card_holder + ", issuer=" + issuer
				+ ", date_expiration=" + date_expiration + "]";
	}
	
	
	


}