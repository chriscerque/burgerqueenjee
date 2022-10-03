package net.ent.etrs.burgerqueenjee.model.daos.impl;


import net.ent.etrs.burgerqueenjee.model.daos.DaoUser;
import net.ent.etrs.burgerqueenjee.model.daos.base.JpaBaseDao;
import net.ent.etrs.burgerqueenjee.model.entities.Role;
import net.ent.etrs.burgerqueenjee.model.entities.User;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class DaoUserImpl extends JpaBaseDao<User, Serializable> implements DaoUser {
    @Override
    public Optional<User> findByLogin(String login) {
        try {
            return Optional.of(this.em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    public List<User> findAllClient() {
        return this.em.createQuery("SELECT u FROM User u WHERE u.role = :role ORDER BY u.login ASC")
                .setParameter("role", Role.USER)
                .getResultList();
    }
}
