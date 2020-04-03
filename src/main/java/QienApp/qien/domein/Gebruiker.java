package QienApp.qien.domein;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@ApiModel(value="Gebruiker", description="Bevat alle waarden van de Gebruiker-entiteit.")
@Inheritance
@Table(name="gebruiker_table")
public class Gebruiker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private long id;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ApiModelProperty(position = 1, value = "De voornaam van een Gebruiker.")
	private String voornaam;
	@ApiModelProperty(position = 2, value = "De achternaam van een Gebruiker.")
	private String achternaam;
	@ApiModelProperty(position = 3, value = "De geboortedatum van een Gebruiker.")
	private String geboorteDatum;
	@ApiModelProperty(position = 4, value = "Het adres van een Gebruiker.")
	private String adres;
	@ApiModelProperty(position = 5, value = "Het emailadres van een Gebruiker.")
	private String email;
	@ApiModelProperty(position = 6, value = "Het telefoonnummer van een Gebruiker.")
	private String telefoonNummer;
	@ApiModelProperty(position = 7, value = "De wachtwoordhash van een Gebruiker.")
	private String wachtwoordHash;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public String getGeboorteDatum() {
		return geboorteDatum;
	}
	public void setGeboorteDatum(String geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefoonNummer() {
		return telefoonNummer;
	}
	public void setTelefoonNummer(String telefoonNummer) {
		this.telefoonNummer = telefoonNummer;
	}
	public String getWachtwoordHash() {
		return wachtwoordHash;
	}
	public void setWachtwoordHash(String wachtwoordHash) {
		this.wachtwoordHash = this.passwordEncoder.encode(wachtwoordHash);
	}
}