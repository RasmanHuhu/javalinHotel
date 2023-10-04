package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public abstract class RoomDAO extends IDAO {

    private static EntityManagerFactory emf;
    private static RoomDAO instance;

    private List<Room> rooms = new ArrayList<>();

    //Singleton pattern - men har vi brug for den i den her? For den klager da den er abstract
    public static RoomDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RoomDAO();
        }
        return instance;
    }

    @Override
    public void create(Room room) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
        }
    }

    @Override
    public Room update(Room room) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(room);
            em.getTransaction().commit();
        }
        return room;
    }

    @Override
    public void delete(Room room) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(room);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Room> findRoom(Room room) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Room> typedQuery = em.createQuery(" :id", Room.class);
            typedQuery.setParameter("id" + "id", Room.getId());
            return typedQuery.getResultList();
        }
    }


}
