package QienApp.qien.controller;

import javax.transaction.Transactional;
import QienApp.qien.domein.Manager;

@Transactional
public interface ManagerRepository extends GebruikerRepository<Manager>{}