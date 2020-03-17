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
	
	public Opdrachtgever addOpdrachtgever(Opdrachtgever opdrachtgever) {
		System.out.println("Opdrachtgever toegevoegd aan Database");
		return opdrachtgeverRepository.save(opdrachtgever);
	}
}