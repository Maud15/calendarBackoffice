package com.m2i.backoffice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(Long id);

    List<T> getAll();

    default T create(T t) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(t);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return t;
    }

    void update(T t);

    void delete(Long id);

    /*

    //Optional<E> findById(Long id);
    default Optional<E> findById(Long elementId, Class<E> elementClass) {
        Optional<E> optRecipe = Optional.empty();
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            optRecipe = Optional.of(em.find(elementClass, elementId));
        } catch(EntityNotFoundException e) {
            e.printStackTrace();
        }
        return optRecipe;
    }

    default E update(E elementToMerge) {
        E result = null;
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            result = em.merge(elementToMerge);
            et.commit();
        } catch(Exception e) {
            et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return result;
    }

    default void deleteById(Long idElementToDelete, Class<E> elementClass) {
        Optional<E> element = this.findById(idElementToDelete, elementClass);
        element.ifPresent(this::delete);
    }

    default void delete(E elementToDelete) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.contains(elementToDelete) ? elementToDelete : em.merge(elementToDelete));
            et.commit();
        } catch(Exception e) {
            System.out.println("Impossible de supprimer l'élément choisi");
            e.printStackTrace();
            if (et.isActive()) { et.rollback(); }
        } finally {
            em.close();
        }
    }*/

}
