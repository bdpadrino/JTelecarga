package sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "email")
public class Mail {
	 
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="incrementIdMail")
		@SequenceGenerator(
		    name="incrementIdMail",
			sequenceName="idMail_seq",
			allocationSize=1)
    	@Column(name = "id")
		private Integer id;
		@Column(name = "usuario")
		private String username;
		@Column(name = "password")
		private String password;  	    
		@Column(name = "host")
		private String host; 
		@Column(name = "direnvio")
		private String dirEnvio;
		@Column(name = "puerto")
		private Integer puerto; 
		@Column(name = "asunto")
		private String asunto; 
		@Column(name = "cuerpo")
		private String cuerpo;
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getDirEnvio() {
			return dirEnvio;
		}
		public void setDirEnvio(String dirEnvio) {
			this.dirEnvio = dirEnvio;
		}
		public Integer getPuerto() {
			return puerto;
		}
		public void setPuerto(Integer puerto) {
			this.puerto = puerto;
		}
		public String getAsunto() {
			return asunto;
		}
		public void setAsunto(String asunto) {
			this.asunto = asunto;
		}
		public String getCuerpo() {
			return cuerpo;
		}
		public void setCuerpo(String cuerpo) {
			this.cuerpo = cuerpo;
		}
		
		@Override
		public String toString() {
			return "Mail [id=" + id + ", username=" + username + ", password=" + password + ", host=" + host
					+ ", dirEnvio=" + dirEnvio + ", puerto=" + puerto + ", asunto=" + asunto + ", cuerpo=" + cuerpo
					+ "]";
		} 
		    	    
	  


		
		
		
}
