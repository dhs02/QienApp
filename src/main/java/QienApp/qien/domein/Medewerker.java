package QienApp.qien.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medewerker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)



	private long id;
	private String voornaam;
	private String achternaam;
	private String geboortedatum;
	private String adres;
	private String email;
	private String telefoonnummer;
	
	//MORGEN EVEN KIJKEN OF DIT WERKT
	Werkgever demijne;
	
	//NO ARGS CONSTRUCTOR --Michiel
	Medewerker(){}
	
	//plak werkg aan medew constructor --Michiel
	public Medewerker(Medewerker m, Werkgever w){
		m = this;
		w = this.demijne;
	}
	//================

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
	public String getGeboortedatum() {
		return geboortedatum;
	}
	public void setGeboortedatum(String geboortedatum) {
		this.geboortedatum = geboortedatum;
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
	public String getTelefoonnummer() {
		return telefoonnummer;
	}
	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}


}
