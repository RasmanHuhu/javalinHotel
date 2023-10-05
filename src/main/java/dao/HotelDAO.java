package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hotel;
import model.Room;

import java.util.List;

public class HotelDAO extends ADAO<Hotel> {

    private static HotelDAO instance;


    public HotelDAO(Class<Hotel> entityClass) {
        super(entityClass);
        queryFindAll = ("SELECT h FROM Hotel h");
    }


    //Singleton pattern -
    public static HotelDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HotelDAO(Hotel.class);

        }
        return instance;
    }

    public List<Room> findAllRooms(int hotelId) {
        try (EntityManager em = emf.createEntityManager()) {
            String rooms = "SELECT r FROM Room r WHERE r.hotel.id = :hotelId";
            TypedQuery<Room> query = em.createQuery(rooms, Room.class);
            query.setParameter("hotelId", hotelId);
            return query.getResultList();
        }
    }
    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
