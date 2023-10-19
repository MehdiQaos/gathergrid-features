package com.gathergrid.gathergridfeatures.repository.interfacesImpl;

import com.gathergrid.gathergridfeatures.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.util.List;


public class crudRepository<T> {

    private final EntityManager entityManager;

    public crudRepository() {
         entityManager = EntityManagerUtil.getEntityManager();
    }



    public void save(T objet){
        entityManager.getTransaction().begin();
        entityManager.persist(objet);
        entityManager.getTransaction().commit();
    }

    public T findById(Class<T> entityClass, Long id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> getAll(Class<T> entityClass) {
        List<T> entities = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        entityManager.close();
        return entities;
    }

    public void update(Class<T> entityClass, Long entityId, T updatedEntity) {
        entityManager.getTransaction().begin();

        T entity = entityManager.find(entityClass, entityId);
        if (entity != null) {
            entityManager.merge(entity);
        }

        entityManager.getTransaction().commit();
    }

    public void delete(Class<T> entityClass, Long entityId) {
        entityManager.getTransaction().begin();

        T entity = entityManager.find(entityClass, entityId);
        if (entity != null) {
            entityManager.remove(entity);
        }

        entityManager.getTransaction().commit();
    }



}

