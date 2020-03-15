package QienApp.qien.controller;

import javax.transaction.Transactional;
import QienApp.qien.domein.Contactpersoon;

@Transactional
public interface ContactpersoonRepository extends GebruikerRepository<Contactpersoon>{}