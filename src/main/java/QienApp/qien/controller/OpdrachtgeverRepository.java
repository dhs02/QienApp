package QienApp.qien.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import QienApp.qien.domein.Opdrachtgever;

	@Component
	public interface OpdrachtgeverRepository extends CrudRepository<Opdrachtgever, Long>{

	}
