package QienApp.qien.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@Table(name="gebruiker_table")
public class Gebruiker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String voornaam;
	private String achternaam;
	private String geboorteDatum;
	private String adres;
	private String email;
	private String telefoonNummer;
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
		this.wachtwoordHash = wachtwoordHash;
	}
}