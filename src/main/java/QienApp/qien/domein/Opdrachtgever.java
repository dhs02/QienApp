package QienApp.qien.domein;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import java.util.Set;

@Entity
@ApiModel(value="Opdrachtgever", description="Bevat alle waarden van de Opdrachtgever-entiteit.")
public class Opdrachtgever {
	public static final String DEFAULT_BEDRIJFSNAAM = "Qien";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String bedrijfsnaam;
	private String adres;
	private String postcode;
	private String plaats;
	private String email;
	private String telefoonNummer;

	@OneToMany
	private Set<Contactpersoon> contactpersonen;

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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
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

	@JsonIgnore
	public Set<Contactpersoon> getContactpersonen() {
		return this.contactpersonen;
	}

	public void setContactpersonen(Set<Contactpersoon> contactpersonen) {
		this.contactpersonen = contactpersonen;
	}
}
