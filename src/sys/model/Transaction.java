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
@Table(name = "system_transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementoIdTransaction")
	@SequenceGenerator(
	    name="incrementoIdTransaction",
		sequenceName="system_transaction_id",
		allocationSize=1)
	@Column(name = "systems_trace_number")									//ID DE LA TRANSACCION
	private Integer systems_trace_number;
	@Column(name = "transaction_type")									
	private String  transaction_type;
	@Column(name = "pos_entry_mode")										//METODO DE ENTRADA DE LA TARJETA AL PUNTO DE VENTA
	private String pos_entry_mode;	
	@Column(name = "terminal_model")
	private String  model;
	@Column(name = "iso")
	private String  iso ;
	@Column(name = "pan")
	private String  pan;													 //NUMERO DE LA TARJETA DE DEBITO
	@Column(name = "card_holder")
	private String  card_holder;                                             //NOMBRE TEJETAHABIENTE
	@Column(name = "issuer")
	private String  issuer;                                                  //EMISOR "VISA"
	@Column(name = "card_acceptor_name")
	private String  card_acceptor_name;                                      //NOMBRE DEL COMERCIO DUENO DEL POS
	@Column(name = "card_acceptor_terminal_id")
	private String  card_acceptor_terminal_id;         						 //SERIAL DEL TERMINAL
	@Column(name = "amount_transaction")
	private Double  amount_transaction;                                      //MONTO DE LA TRANSACCION
	@Column(name = "time_transaction")										
	private Date  	time_transaction;  									     //HORA DE LA TRANSACCION
	@Column(name = "date_transaction")										
	private Date	date_transaction;  										 //FECHA DE LA TRANSACCION
	@Column(name = "date_expiration")										
	private Date	date_expiration;  										 //FECHA DE EXPIRACION DE LA TARJETA
	@Column(name = "currency")												
	private String  currency;  												 //MONEDA
	@Column(name = "place")												
	private String  place;  												 //LUGAR DE LA TRANSACCION

	public Transaction(){

	}

	public Transaction(Integer systems_trace_number, String transaction_type, String pos_entry_mode, String model,
			String iso, String pan, String card_holder, String issuer, String card_acceptor_name,
			String card_acceptor_terminal_id, Double amount_transaction, Date time_transaction, Date date_transaction,
			Date date_expiration, String currency, String place) {
		super();
		this.systems_trace_number = systems_trace_number;
		this.transaction_type = transaction_type;
		this.pos_entry_mode = pos_entry_mode;
		this.model = model;
		this.iso = iso;
		this.pan = pan;
		this.card_holder = card_holder;
		this.issuer = issuer;
		this.card_acceptor_name = card_acceptor_name;
		this.card_acceptor_terminal_id = card_acceptor_terminal_id;
		this.amount_transaction = amount_transaction;
		this.time_transaction = time_transaction;
		this.date_transaction = date_transaction;
		this.date_expiration = date_expiration;
		this.currency = currency;
		this.place = place;
	}





	public Integer getSystems_trace_number() {
		return systems_trace_number;
	}


	public void setSystems_trace_number(Integer systems_trace_number) {
		this.systems_trace_number = systems_trace_number;
	}


	public String getTransaction_type() {
		return transaction_type;
	}


	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}


	public String getPos_entry_mode() {
		return pos_entry_mode;
	}


	public void setPos_entry_mode(String pos_entry_mode) {
		this.pos_entry_mode = pos_entry_mode;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getIso() {
		return iso;
	}


	public void setIso(String iso) {
		this.iso = iso;
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

	public String getCard_acceptor_name() {
		return card_acceptor_name;
	}

	public void setCard_acceptor_name(String card_acceptor_name) {
		this.card_acceptor_name = card_acceptor_name;
	}

	public String getCard_acceptor_terminal_id() {
		return card_acceptor_terminal_id;
	}

	public void setCard_acceptor_terminal_id(String card_acceptor_terminal_id) {
		this.card_acceptor_terminal_id = card_acceptor_terminal_id;
	}

	public Double getAmount_transaction() {
		return amount_transaction;
	}

	public void setAmount_transaction(Double amount_transaction) {
		this.amount_transaction = amount_transaction;
	}

	public Date getTime_transaction() {
		return time_transaction;
	}





	public void setTime_transaction(Date time_transaction) {
		this.time_transaction = time_transaction;
	}





	public Date getDate_transaction() {
		return date_transaction;
	}

	public void setDate_transaction(Date date_transaction) {
		this.date_transaction = date_transaction;
	}

	public Date getDate_expiration() {
		return date_expiration;
	}



	public void setDate_expiration(Date date_expiration) {
		this.date_expiration = date_expiration;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Transaction [systems_trace_number=" + systems_trace_number + ", transaction_type=" + transaction_type
				+ ", pos_entry_mode=" + pos_entry_mode + ", model=" + model + ", iso=" + iso + ", pan=" + pan
				+ ", card_holder=" + card_holder + ", issuer=" + issuer + ", card_acceptor_name=" + card_acceptor_name
				+ ", card_acceptor_terminal_id=" + card_acceptor_terminal_id + ", amount_transaction="
				+ amount_transaction + ", time_transaction=" + time_transaction + ", date_transaction="
				+ date_transaction + ", date_expiration=" + date_expiration + ", currency=" + currency + ", place="
				+ place + "]";
	}

}