package net.ent.etrs.burgerqueenjee.model.facades;


import net.ent.etrs.burgerqueenjee.model.daos.DaoClient;
import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.Client;
import net.ent.etrs.burgerqueenjee.model.exceptions.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import javax.inject.Inject;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientFacadeImpl {

    @Inject
    private DaoClient daoClient;


    private void rechercherCreerClient(Client c) {

        try {
            if (this.daoClient.searchClientByNomPrenom(c.getNom(), c.getPrenom()).isEmpty()) {
                this.daoClient.save(c);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }


    public Optional<Client> searchClientByNomPrenom(String nom, String prenom) throws BusinessException {
        try {
            return this.daoClient.searchClientByNomPrenom(nom, prenom);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


    public Set<Client> findAll() throws BusinessException {
        try {
            return IterableUtils.toList(this.daoClient.findAll()).stream().collect(Collectors.toSet());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public Optional<Client> save(Client c) throws BusinessException {
        try {
            return this.daoClient.save(c);
        } catch (DaoException | NoSuchElementException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


    public void delete(Long id) throws BusinessException {
        try {
            this.daoClient.delete(id);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


}
