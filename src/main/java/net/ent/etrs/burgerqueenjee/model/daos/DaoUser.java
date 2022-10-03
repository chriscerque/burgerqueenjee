package net.ent.etrs.burgerqueenjee.model.daos;


import net.ent.etrs.burgerqueenjee.model.daos.base.BaseDao;
import net.ent.etrs.burgerqueenjee.model.entities.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DaoUser extends BaseDao<User, Serializable> {
    Optional<User> findByLogin(String login);
    
    List<User> findAllClient();
}
