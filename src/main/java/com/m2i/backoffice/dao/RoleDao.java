package com.m2i.backoffice.dao;


import com.m2i.backoffice.model.Role;
import com.m2i.backoffice.model.RoleEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class RoleDao implements Dao<Role>{

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Optional<Role> get(Long id) {
        Optional<Role> optRole = Optional.empty();
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            optRole = Optional.of(em.find(Role.class, id));
        } catch(EntityNotFoundException e) {
            e.printStackTrace();
        }
        return optRole;
    }

    @Override
    public void delete(Long id) {

    }

    public Optional<Role> getByName(String name) {
        Optional<Role> optRole = Optional.empty();
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            String roleNameQuery = "from Role where name = :roleName";
            optRole = Optional.of(em.createQuery(roleNameQuery, Role.class)
                    .setParameter("roleName", RoleEnum.valueOf(name))
                    .getSingleResult());
        } catch(EntityNotFoundException e) {
            e.printStackTrace();
        }
        return optRole;
    }
}
