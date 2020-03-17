package QienApp.qien.controller;

import javax.transaction.Transactional;
import QienApp.qien.domein.Admin;

@Transactional
public interface AdminRepository extends GebruikerRepository<Admin>{}