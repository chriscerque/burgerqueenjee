package net.ent.etrs.burgerqueenjee.model.daos;


import net.ent.etrs.burgerqueenjee.model.daos.base.BaseDao;
import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.Client;
import net.ent.etrs.burgerqueenjee.model.entities.Commande;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DaoCommande extends BaseDao<Commande, Serializable> {

//	public void initialisation(ClientFacade facadeClient, ProduitFacade facadeProduit) throws BusinessException;

    Optional<Commande> findByIdWithProduits(Long id);

    List<Commande> findAll(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);

    int count(Map<String, FilterMeta> filterBy);

    Iterable<Commande> searchCommandeByClient(Client client) throws DaoException;

}
