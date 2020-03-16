package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import QienApp.qien.domein.Opdrachtgever;

@Service
@Transactional
public class OpdrachtgeverService {
	@Autowired
	OpdrachtgeverRepository d;
	
	public void inService(Opdrachtgever x) {
		System.out.println("Opdrachtgever toegevoegd aan Database");
		d.save(x);
	}
}