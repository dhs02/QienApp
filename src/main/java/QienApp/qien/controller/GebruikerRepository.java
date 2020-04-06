package QienApp.qien.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import QienApp.qien.domein.Gebruiker;

@Repository
public interface GebruikerRepository<T extends Gebruiker> extends CrudRepository<T, Long> {
	List<T> findByAchternaam(String achternaam);
	List<T> findByVoornaam(String voornaam);
	Optional<Gebruiker> findByEmail(String email);
//	long countByLastname(String achternaam);
}