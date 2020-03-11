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

	public void inService() {
		System.out.println("Medewerker toegevoegd aan Database");
		d.save(new Medewerker());
	}
}
