package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import QienApp.qien.domein.Medewerker;
@Service
@Transactional
public class MedewerkerService {

	@Autowired
	MedewerkerRepository d;
	
	public Iterable<Medewerker> getMedewerker() {
		Iterable<Medewerker> medewerkers;
		medewerkers = d.findAll();
		return medewerkers;
	}
	
	public void inService(Medewerker x) {
		System.out.println("Medewerker toegevoegd aan Database");
		d.save(x);
	}
	
	public void verwijderen(Long userId) {
		System.out.println("Medewerker verwijderd uit Database");
		d.deleteById(userId);
	}
	
}
