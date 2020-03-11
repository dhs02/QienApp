package QienApp.qien.controller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import QienApp.qien.domein.Medewerker;

	@Component
	public interface MedewerkerRepository extends CrudRepository<Medewerker, Long>{

	}
