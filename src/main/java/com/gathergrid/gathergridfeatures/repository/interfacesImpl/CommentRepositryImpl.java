package com.gathergrid.gathergridfeatures.repository.interfacesImpl;

import com.gathergrid.gathergridfeatures.domain.Comment;
import com.gathergrid.gathergridfeatures.repository.interfaces.CommentRepositry;
import com.gathergrid.gathergridfeatures.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CommentRepositryImpl implements CommentRepositry {
    private final EntityManager em=EntityManagerUtil.getEntityManager();
    @Override
    public Comment save(Comment c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }

    @Override
    public Comment findById(Long id) {
        return em.find(Comment.class,id);
    }
    @Override
    public Comment update(Comment c) {
         em.getTransaction().begin();
         em.merge(c);
         em.getTransaction().commit();
         return c;
    }

    @Override
    public void delete(Long comment_id) {
        Comment comment = em.find(Comment.class, comment_id);
        em.remove(comment);
    }
    @Override
    public List<Comment> show(Long event_id) {
        Query query = em.createQuery("select c from Comment c WHERE c.event.id = :event_id",Comment.class);
        query.setParameter("event_id", event_id);
        return query.getResultList();
    }
}
