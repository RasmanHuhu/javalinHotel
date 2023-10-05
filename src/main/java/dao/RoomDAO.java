package dao;

import jakarta.persistence.EntityManagerFactory;
import model.Room;

public class RoomDAO extends ADAO<Room> {

    private static EntityManagerFactory emf;
    private static RoomDAO instance;


    public RoomDAO(Class<Room> entityClass) {
        super(entityClass);
        queryFindAll = ("SELECT r FROM Room r");
    }

    //Singleton pattern - men har vi brug for den i den her? For den klager da den er abstract
    public static RoomDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RoomDAO(Room.class);
        }
        return instance;
    }


}
