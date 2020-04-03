package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import QienApp.qien.domein.Opdrachtgever;

@Service
@Transactional
public class OpdrachtgeverService {
	@Autowired
	OpdrachtgeverRepository opdrachtgeverRepository;
	
	public Iterable<Opdrachtgever> getAllOpdrachtgevers() {
		return opdrachtgeverRepository.findAll();
	}
	public Opdrachtgever getOpdrachtgeverById(Long userId) {
		System.out.println("Opdrachtgever gevonden in Database");
		return opdrachtgeverRepository.findById(userId).get();
	}
	public Opdrachtgever addOpdrachtgever(Opdrachtgever opdrachtgever) {
		System.out.println("Opdrachtgever toegevoegd aan Database");
		return opdrachtgeverRepository.save(opdrachtgever);
	}
	public void deleteOpdrachtgever(Long userId) {
		System.out.println("Opdrachtgever verwijderd uit Database");
		opdrachtgeverRepository.deleteById(userId);
	}
	public Opdrachtgever updateOpdrachtgever(Long userId, Opdrachtgever opdrachtgeverDetails) {
		Opdrachtgever opdrachtgever = opdrachtgeverRepository.findById(userId).get();
		if (opdrachtgeverDetails.getBedrijfsnaam() != null && opdrachtgeverDetails.getBedrijfsnaam() != "") {
			opdrachtgever.setBedrijfsnaam(opdrachtgeverDetails.getBedrijfsnaam());
		}
		if (opdrachtgeverDetails.getAdres() != null && opdrachtgeverDetails.getAdres() != "") {
			opdrachtgever.setAdres(opdrachtgeverDetails.getAdres());
		}
		if (opdrachtgeverDetails.getPostcode() != null && opdrachtgeverDetails.getPostcode() != "") {
			opdrachtgever.setPostcode(opdrachtgeverDetails.getPostcode());
		}
		if (opdrachtgeverDetails.getPlaats() != null && opdrachtgeverDetails.getPlaats() != "") {
			opdrachtgever.setPlaats(opdrachtgeverDetails.getPlaats());
		}
		if (opdrachtgeverDetails.getEmail() != null && opdrachtgeverDetails.getEmail() != "") {
			opdrachtgever.setEmail(opdrachtgeverDetails.getEmail());
		}
		if (opdrachtgeverDetails.getTelefoonNummer() != null && opdrachtgeverDetails.getTelefoonNummer() != "") {
			opdrachtgever.setTelefoonNummer(opdrachtgeverDetails.getTelefoonNummer());
		}
	    System.out.println("Opdrachtgever veranderd in Database");
	    return opdrachtgeverRepository.save(opdrachtgever);
	}
}