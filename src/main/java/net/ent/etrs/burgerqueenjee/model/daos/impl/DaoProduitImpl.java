package net.ent.etrs.burgerqueenjee.model.daos.impl;


import net.ent.etrs.burgerqueenjee.model.daos.DaoProduit;
import net.ent.etrs.burgerqueenjee.model.daos.Req;
import net.ent.etrs.burgerqueenjee.model.daos.base.JpaBaseDao;
import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.Produit;
import net.ent.etrs.burgerqueenjee.model.entities.references.TailleProduit;
import net.ent.etrs.burgerqueenjee.model.entities.references.TypeProduit;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DaoProduitImpl extends JpaBaseDao<Produit, Serializable> implements DaoProduit {
    
    @Override
    public List<Produit> findAll(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    
        String sql = "SELECT p FROM Produit p WHERE 1=1 ";
    
        String libelle = null;
        Float prixHT = null;
        Float poidsKg = null;
        TypeProduit typeProduit = null;
        TailleProduit tailleProduit = null;
    
        if (filterBy.containsKey("libelle")) {
            libelle = (String) filterBy.get("libelle").getFilterValue();
        }
    
        if (filterBy.containsKey("prixHT")) {
            prixHT = Float.parseFloat((String) filterBy.get("prixHT").getFilterValue());
        }
    
        if (filterBy.containsKey("poidsKg")) {
            poidsKg = Float.parseFloat((String) filterBy.get("poidsKg").getFilterValue());
        }
    
        if (filterBy.containsKey("typeProduit")) {
            typeProduit = (TypeProduit) filterBy.get("typeProduit").getFilterValue();
        }
    
        if (filterBy.containsKey("tailleProduit")) {
            tailleProduit = (TailleProduit) filterBy.get("tailleProduit").getFilterValue();
        }
    
        if (libelle != null) {
            sql += " AND LOWER(p.libelle) LIKE :libelle ";
        }
    
        if (prixHT != null) {
            sql += " AND p.prixHT = :prixHT ";
        }
    
        if (poidsKg != null) {
            sql += " AND p.poidsKg = :poidsKg ";
        }
    
        if (typeProduit != null) {
            sql += " AND p.categorie = :categorie ";
        }
    
        if (tailleProduit != null) {
            sql += " AND p.marque = :marque ";
        }
    
        if (!sortBy.isEmpty()) {
            sql += " ORDER BY ";
            for(Map.Entry<String, SortMeta> sort : sortBy.entrySet()) {
                sql += " p." + sort.getValue().getField() + " " + (sort.getValue().getOrder().equals(SortOrder.ASCENDING) ? "ASC" : "DESC") + ",";
            }
            sql = sql.substring(0, sql.length() -1);
        } else {
            sql += " ORDER BY p.libelle ASC ";
        }
    
        TypedQuery<Produit> q = this.em.createQuery(sql, Produit.class);
        
        if (libelle != null) {
            q.setParameter("libelle", libelle.toLowerCase()+"%");
        }
    
        if (prixHT != null) {
            q.setParameter("prixHT", prixHT);
        }
    
        if (poidsKg != null) {
            q.setParameter("poidsKg", poidsKg);
        }
    
        if (typeProduit != null) {
            q.setParameter("categorie", typeProduit);
        }
    
        if (tailleProduit != null) {
            q.setParameter("marque", tailleProduit);
        }
    
        q.setFirstResult(first);
        q.setMaxResults(pageSize);
    
        return q.getResultList();
    }
    
    @Override
    public int count(Map<String, FilterMeta> filterBy) {
    
        String sql = "SELECT COUNT(p) FROM Produit p WHERE 1=1 ";
    
        String libelle = null;
        Float prixHT = null;
        Float poidsKg = null;
        TypeProduit typeProduit = null;
        TailleProduit tailleProduit = null;
    
        if (filterBy.containsKey("libelle")) {
            libelle = (String) filterBy.get("libelle").getFilterValue();
        }
    
        if (filterBy.containsKey("prixHT")) {
            prixHT = Float.parseFloat((String) filterBy.get("prixHT").getFilterValue());
        }
    
        if (filterBy.containsKey("poidsKg")) {
            poidsKg = Float.parseFloat((String) filterBy.get("poidsKg").getFilterValue());
        }
    
        if (filterBy.containsKey("categorie")) {
            typeProduit = (TypeProduit) filterBy.get("typeProduit").getFilterValue();
        }
    
        if (filterBy.containsKey("marque")) {
            tailleProduit = (TailleProduit) filterBy.get("tailleProduit").getFilterValue();
        }
    
        if (libelle != null) {
            sql += " AND LOWER(p.libelle) LIKE :libelle ";
        }
    
        if (prixHT != null) {
            sql += " AND p.prixHT = :prixHT ";
        }
    
        if (poidsKg != null) {
            sql += " AND p.poidsKg = :poidsKg ";
        }
    
        if (typeProduit != null) {
            sql += " AND p.typeProduit = :typeProduit ";
        }
    
        if (tailleProduit != null) {
            sql += " AND p.tailleProduit = :tailleProduit ";
        }
    
        TypedQuery<Long> q = this.em.createQuery(sql, Long.class);
    
        if (libelle != null) {
            q.setParameter("libelle", libelle.toLowerCase()+"%");
        }
    
        if (prixHT != null) {
            q.setParameter("prixHT", prixHT);
        }
    
        if (poidsKg != null) {
            q.setParameter("poidsKg", poidsKg);
        }
    
        if (typeProduit != null) {
            q.setParameter("typeProduit", typeProduit);
        }
    
        if (tailleProduit != null) {
            q.setParameter("tailleProduit", tailleProduit);
        }
    
        return q.getSingleResult().intValue();
    }

    @Override
    public Optional<Produit> searchProduitByNomPrenom(String nom, TypeProduit typeProduit, TailleProduit tailleProduit) throws DaoException {

        try {
            TypedQuery<Produit> query = super.getEm()
                    .createQuery(Req.PRODUIT_BY_NOM_TYPE_TAILLE, Produit.class);
            query.setParameter("nom", nom);
            query.setParameter("typeProduit", typeProduit);
            query.setParameter("tailleProduit", tailleProduit);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException | IllegalStateException | QueryTimeoutException |
                 TransactionRequiredException | IllegalArgumentException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
