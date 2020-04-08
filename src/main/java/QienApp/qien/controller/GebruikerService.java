package QienApp.qien.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import QienApp.qien.domein.Gebruiker;

@Service
@Transactional
public class GebruikerService {
	@Autowired
	GebruikerRepository<Gebruiker> gebruikerRepository;
	
	public List<Gebruiker> findByVoornaam(String voornaam) {
		return gebruikerRepository.findByVoornaam(voornaam);
	}
	public List<Gebruiker> findByAchternaam(String achternaam) {
		return gebruikerRepository.findByAchternaam(achternaam);
	}
	
	public Optional<Gebruiker> findById(Long id) {
		return this.gebruikerRepository.findById(id);
	}

	public Iterable<Gebruiker> getAllGebruikers() {
		return gebruikerRepository.findAll();
	}
	public Gebruiker getGebruikerById(Long userId) {
		System.out.println("Gebruiker gevonden in Database");
		return gebruikerRepository.findById(userId).get();
	}
	public Gebruiker addGebruiker(Gebruiker gebruiker) {
		System.out.println("Gebruiker toegevoegd aan Database");
		return gebruikerRepository.save(gebruiker);
	}
	public void deleteGebruiker(Long userId) {
		System.out.println("Gebruiker verwijderd uit Database");
		gebruikerRepository.deleteById(userId);
	}
	public Gebruiker updateGebruiker(Long userId, Gebruiker gebruikerDetails) {
		Gebruiker gebruiker = gebruikerRepository.findById(userId).get();

		if (gebruikerDetails.getVoornaam() != null && gebruikerDetails.getVoornaam() != "") {
			gebruiker.setVoornaam(gebruikerDetails.getVoornaam());
		}
		if (gebruikerDetails.getAchternaam() != null && gebruikerDetails.getAchternaam() != "") {
			gebruiker.setAchternaam(gebruikerDetails.getAchternaam());
		}
		if (gebruikerDetails.getGeboorteDatum() != null && gebruikerDetails.getGeboorteDatum() != "") {
			gebruiker.setGeboorteDatum(gebruikerDetails.getGeboorteDatum());
		}
		if (gebruikerDetails.getAdres() != null && gebruikerDetails.getAdres() != "") {
			gebruiker.setAdres(gebruikerDetails.getAdres());
		}
		if (gebruikerDetails.getEmail() != null && gebruikerDetails.getEmail() != "") {
			gebruiker.setEmail(gebruikerDetails.getEmail());
		}
		if (gebruikerDetails.getTelefoonNummer() != null && gebruikerDetails.getTelefoonNummer() != "") {
			gebruiker.setTelefoonNummer(gebruikerDetails.getTelefoonNummer());
		}
		if (gebruikerDetails.getWachtwoordHash() != null && gebruikerDetails.getWachtwoordHash() != "") {
			gebruiker.setWachtwoordHash(gebruikerDetails.getWachtwoordHash());
		}
	    System.out.println("Gebruiker veranderd in Database");
	    return gebruikerRepository.save(gebruiker);
	}

	public Gebruiker getByEmail(String email) {
		System.out.println("GetByEmail in gebruikerService aangeroepen");
		return gebruikerRepository.findByEmail(email).get();
	}
}