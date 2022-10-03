package net.ent.etrs.burgerqueenjee.model.daos;


import net.ent.etrs.burgerqueenjee.model.daos.base.BaseDao;
import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.Client;

import java.io.Serializable;
import java.util.Optional;

public interface DaoClient extends BaseDao<Client, Serializable> {

//	void initialisationClient();

    Optional<Client> searchClientByNomPrenom(String nom, String prenom) throws DaoException;

}
