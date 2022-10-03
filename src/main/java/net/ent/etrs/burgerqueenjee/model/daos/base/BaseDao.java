package net.ent.etrs.burgerqueenjee.model.daos.base;


import net.ent.etrs.burgerqueenjee.model.daos.exceptions.DaoException;
import net.ent.etrs.burgerqueenjee.model.entities.AbstractEntity;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity, ID extends Serializable> {

    Optional<T> find(ID id) throws DaoException;

    Iterable<T> findAll() throws DaoException;

    Optional<T> save(T entity) throws DaoException;

    void delete(ID id) throws DaoException;

    boolean exists(ID id) throws DaoException;

    long count() throws DaoException;
}
