package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import QienApp.qien.domein.Werkgever;
@Service
@Transactional
public class WerkgeverService {

	@Autowired
	WerkgeverRepository d;
	
	public void inService(Werkgever x) {
		System.out.println("Werkgever toegevoegd aan Database");
		d.save(x);
	}
}
