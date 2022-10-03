package net.ent.etrs.burgerqueenjee.model.daos.impl;


import net.ent.etrs.burgerqueenjee.model.daos.DaoClient;
import net.ent.etrs.burgerqueenjee.model.daos.Req;
import net.ent.etrs.burgerqueenjee.model.daos.base.JpaBaseDao;
import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.Client;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class DaoClientJPAImpl extends JpaBaseDao<Client, Serializable> implements DaoClient {

//	@Override
//	public void initialisationClient() {
//		List<Client> lstClients = new  ArrayList<>();
//
//		lstClients.add(EntitiesFactory.fabriquerClient("toto", "totop", "toto@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("titi", "titip", "titi@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("alf", "raid", "alf@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("dark", "queen", "dark.queen@g"));
//		lstClients.add(EntitiesFactory.fabriquerClient("Udutorse", "paul", "paul.udutore@g"));
//
//
//		lstClients.forEach((p)->{
//			try {
//				rechercherCreerClient(p);
//			} catch (DaoException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//	}

	private void rechercherCreerClient(Client c) throws DaoException {
		if (Objects.isNull(this.searchClientByNomPrenom(c.getNom(), c.getPrenom()))) {
			this.save(c);
		}
	}


	@Override
	public Optional<Client> searchClientByNomPrenom(String nom, String prenom) throws DaoException {

		try {
			TypedQuery<Client> query = super.getEm()
					.createQuery(Req.CLIENT_BY_NOM_PRENOM, Client.class);
			query.setParameter("nom", nom);
			query.setParameter("prenom", prenom);
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		} catch (NonUniqueResultException | IllegalStateException | QueryTimeoutException |
				 TransactionRequiredException | IllegalArgumentException e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

}
