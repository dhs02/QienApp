package QienApp.qien.controller;

import javax.transaction.Transactional;
import QienApp.qien.domein.Contactpersoon;
import QienApp.qien.domein.Opdrachtgever;

@Transactional
public interface ContactpersoonRepository extends GebruikerRepository<Contactpersoon>{
    public abstract Iterable<Contactpersoon> findAllByOpdrachtgever(Opdrachtgever opdrachtgever);
}