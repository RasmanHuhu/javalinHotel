package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends IDAO {

    private List<Room> rooms = new ArrayList<>();

    public static IDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyDAOImpl();
        }
        return instance;
    }

    @Override
    public void createRoom(Room room) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
        }
    }

    @Override
    public Room updateRoom(Room room) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(room);
            em.getTransaction().commit();
        }
        return room;
    }

    @Override
    public void deleteRoom(Room room) {
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
