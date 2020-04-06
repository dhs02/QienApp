package QienApp.qien.controller;

import java.util.List;

import javax.transaction.Transactional;
import QienApp.qien.domein.Medewerker;

@Transactional
public interface MedewerkerRepository extends GebruikerRepository<Medewerker>{
	List<Medewerker> findByVoornaam(String voornaam);
	List<Medewerker> findByAchternaam(String achternaam);
	List<Medewerker> findByVoornaamAndAchternaam(String voornaam, String achternaam);
}