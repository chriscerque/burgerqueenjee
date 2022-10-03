package net.ent.etrs.burgerqueenjee.model.daos;


import net.ent.etrs.burgerqueenjee.model.daos.base.BaseDao;
import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.Produit;
import net.ent.etrs.burgerqueenjee.model.entities.references.TailleProduit;
import net.ent.etrs.burgerqueenjee.model.entities.references.TypeProduit;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DaoProduit extends BaseDao<Produit, Serializable> {

	List<Produit> findAll(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);

	int count(Map<String, FilterMeta> filterBy);

	Optional<Produit> searchProduitByNomPrenom(String nom, TypeProduit typeProduit, TailleProduit tailleProduit) throws DaoException;

}
