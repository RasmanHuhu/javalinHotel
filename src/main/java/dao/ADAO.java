package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

//Når det er en generic class vi implementer, så skal objektet ikke præciseres
//I dette tilfælde er objektet bare T og kaldes obj

public abstract class ADAO<T> implements IDAO<T> {

    private static EntityManagerFactory emf;
    final Class<T> entityClass;

    protected String queryFindAll;

    @Override
    public void create(T obj) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(T obj) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(T obj) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
        }
    }

    @Override
    public T findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return  em.find(entityClass, id);
        }
    }


@Override
    public List<T> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<T> typedQuery = em.createQuery(queryFindAll, entityClass);
            return typedQuery.getResultList();
        }
    }

    public ADAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
