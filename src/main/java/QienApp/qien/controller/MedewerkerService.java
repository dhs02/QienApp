package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import QienApp.qien.domein.Medewerker;

@Service
@Transactional
public class MedewerkerService {
	@Autowired
	MedewerkerRepository medewerkerRepository;
	
	public Iterable<Medewerker> getAllMedewerkers() {
		return medewerkerRepository.findAll();
	}
	public Medewerker getMedewerkerById(Long userId) {
		System.out.println("Medewerker gevonden in Database");
		return medewerkerRepository.findById(userId).get();
	}
	public Medewerker addMedewerker(Medewerker medewerker) {
		System.out.println("Medewerker toegevoegd aan Database");
		return medewerkerRepository.save(medewerker);
	}
	public void deleteMedewerker(Long userId) {
		System.out.println("Medewerker verwijderd uit Database");
		medewerkerRepository.deleteById(userId);
	}
	public Medewerker updateMedewerker(Long userId, Medewerker medewerkerDetails) {
		Medewerker medewerker = medewerkerRepository.findById(userId).get();
		if (medewerkerDetails.getVoornaam() != null && medewerkerDetails.getVoornaam() != "") {
			medewerker.setVoornaam(medewerkerDetails.getVoornaam());
		}
		if (medewerkerDetails.getAchternaam() != null && medewerkerDetails.getAchternaam() != "") {
			medewerker.setAchternaam(medewerkerDetails.getAchternaam());
		}
		if (medewerkerDetails.getGeboorteDatum() != null && medewerkerDetails.getGeboorteDatum() != "") {
			medewerker.setGeboorteDatum(medewerkerDetails.getGeboorteDatum());
		}
		if (medewerkerDetails.getAdres() != null && medewerkerDetails.getAdres() != "") {
			medewerker.setAdres(medewerkerDetails.getAdres());
		}
		if (medewerkerDetails.getEmail() != null && medewerkerDetails.getEmail() != "") {
			medewerker.setEmail(medewerkerDetails.getEmail());
		}
		if (medewerkerDetails.getTelefoonNummer() != null && medewerkerDetails.getTelefoonNummer() != "") {
			medewerker.setTelefoonNummer(medewerkerDetails.getTelefoonNummer());
		}
		if (medewerkerDetails.getWachtwoordHash() != null && medewerkerDetails.getWachtwoordHash() != "") {
			medewerker.setWachtwoordHash(medewerkerDetails.getWachtwoordHash());
		}
	    System.out.println("Medewerker veranderd in Database");
	    return medewerkerRepository.save(medewerker);
	}
}