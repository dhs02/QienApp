package QienApp.qien.controller;

import javax.transaction.Transactional;
import QienApp.qien.domein.Medewerker;

@Transactional
public interface MedewerkerRepository extends GebruikerRepository<Medewerker>{}