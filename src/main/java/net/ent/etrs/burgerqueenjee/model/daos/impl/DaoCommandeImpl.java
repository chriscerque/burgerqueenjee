package net.ent.etrs.burgerqueenjee.model.daos.impl;


import net.ent.etrs.burgerqueenjee.model.daos.DaoCommande;
import net.ent.etrs.burgerqueenjee.model.daos.Req;
import net.ent.etrs.burgerqueenjee.model.daos.base.JpaBaseDao;
import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.Client;
import net.ent.etrs.burgerqueenjee.model.entities.Commande;
import net.ent.etrs.burgerqueenjee.model.entities.EtatCommande;
import net.ent.etrs.burgerqueenjee.model.entities.User;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DaoCommandeImpl extends JpaBaseDao<Commande, Serializable> implements DaoCommande {
    
    @Override
    public Optional<Commande> findByIdWithProduits(Long id) {
        try {
            return Optional.of(this.em.createQuery("SELECT c FROM Commande c LEFT JOIN FETCH c.produits WHERE c.id = :id", Commande.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    public List<Commande> findAll(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        
        String sql = "SELECT c FROM Commande c WHERE 1=1 ";
        
        Float prix = null;
        User user = null;
        LocalDate createdAt = null;
        EtatCommande etat = null;
        
        if (filterBy.containsKey("prix")) {
            prix = Float.parseFloat((String) filterBy.get("prix").getFilterValue());
        }
        
        if (filterBy.containsKey("user")) {
            user = (User) filterBy.get("user").getFilterValue();
        }
        
        if (filterBy.containsKey("createdAt")) {
            createdAt = (LocalDate) filterBy.get("createdAt").getFilterValue();
        }
    
        if (filterBy.containsKey("etat")) {
            etat = (EtatCommande) filterBy.get("etat").getFilterValue();
        }
        
        if (prix != null) {
            sql += " AND c.prix = :prix ";
        }
        
        if (user != null) {
            sql += " AND c.user = :user ";
        }
        
        if (createdAt != null) {
            sql += " AND DATE(c.createdAt) = :createdAt ";
        }
    
        if (etat != null) {
            sql += " AND c.etat = :etat ";
        }
        
        if (!sortBy.isEmpty()) {
            sql += " ORDER BY ";
            for(Map.Entry<String, SortMeta> sort : sortBy.entrySet()) {
                sql += " c." + sort.getValue().getField() + " " + (sort.getValue().getOrder().equals(SortOrder.ASCENDING) ? "ASC" : "DESC") + ",";
            }
            sql = sql.substring(0, sql.length() -1);
        } else {
            sql += " ORDER BY c.createdAt ASC ";
        }
        
        TypedQuery<Commande> q = this.em.createQuery(sql, Commande.class);
    
        if (prix != null) {
            q.setParameter("prix", prix);
        }
    
        if (user != null) {
            q.setParameter("user", user);
        }
    
        if (createdAt != null) {
            q.setParameter("createdAt", createdAt);
        }
    
        if (etat != null) {
            q.setParameter("etat", etat);
        }
        
        q.setFirstResult(first);
        q.setMaxResults(pageSize);
        
        return q.getResultList();
    }
    
    @Override
    public int count(Map<String, FilterMeta> filterBy) {
    
        String sql = "SELECT COUNT(c) FROM Commande c WHERE 1=1 ";
    
        Float prix = null;
        User user = null;
        LocalDate createdAt = null;
        EtatCommande etat = null;
    
        if (filterBy.containsKey("prix")) {
            prix = Float.parseFloat((String) filterBy.get("prix").getFilterValue());
        }
    
        if (filterBy.containsKey("user")) {
            user = (User) filterBy.get("user").getFilterValue();
        }
    
        if (filterBy.containsKey("createdAt")) {
            createdAt = (LocalDate) filterBy.get("createdAt").getFilterValue();
        }
    
        if (filterBy.containsKey("etat")) {
            etat = (EtatCommande) filterBy.get("etat").getFilterValue();
        }
    
        if (prix != null) {
            sql += " AND c.prix = :prix ";
        }
    
        if (user != null) {
            sql += " AND c.user = :user ";
        }
    
        if (createdAt != null) {
            sql += " AND DATE(c.createdAt) = :createdAt ";
        }
    
        if (etat != null) {
            sql += " AND c.etat = :etat ";
        }
    
        TypedQuery<Long> q = this.em.createQuery(sql, Long.class);
    
        if (prix != null) {
            q.setParameter("prix", prix);
        }
    
        if (user != null) {
            q.setParameter("user", user);
        }
    
        if (createdAt != null) {
            q.setParameter("createdAt", createdAt);
        }
    
        if (etat != null) {
            q.setParameter("etat", etat);
        }
    
        return q.getSingleResult().intValue();
    }

    @Override
    public Iterable<Commande> searchCommandeByClient(Client client) throws DaoException {
        List<Commande> lst = new ArrayList<>();
        try {
            TypedQuery<Commande> query = super.getEm()
                    .createQuery(Req.COMMANDE_PAR_CLIENT, Commande.class);
            query.setParameter("client", client);
            return query.getResultList();
        } catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
