package com.gathergrid.gathergridfeatures.repository.interfacesImpl;

import com.gathergrid.gathergridfeatures.domain.Ticket;
import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.Optional;

public class UserRepository<T> extends crudRepository<T> {
    public Optional<User> findByEmail(User user) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            User user1 = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
            return Optional.ofNullable(user1);
        }catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
