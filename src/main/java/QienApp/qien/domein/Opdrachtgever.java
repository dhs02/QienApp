package QienApp.qien.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="Opdrachtgever", description="Bevat alle waarden van de Opdrachtgever-entiteit.")
public class Opdrachtgever {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String bedrijfsnaam;
	private String adres;
	private String email;
	private String telefoonnummer;
	private String plaats;
	private String postcode;

	public String getPlaats() {
		return plaats;
	}
	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBedrijfsnaam() {
		return bedrijfsnaam;
	}
	public void setBedrijfsnaam(String bedrijfsnaam) {
		this.bedrijfsnaam = bedrijfsnaam;
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