package dao;

import jakarta.persistence.EntityManagerFactory;
import model.Hotel;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public abstract class HotelDAO extends IDAO {

    private static EntityManagerFactory emf;
    private static HotelDAO instance;

    //Arraylist med hoteller
    private List<Hotel> hotels = new ArrayList<>();

    public static HotelDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HotelDAO();

        }
        return instance;
    }

    @Override
    public void create(Hotel entity) {

        hotels.add(entity);
    }

    @Override
    public Hotel getById(int id) {

        return hotels.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Hotel> getAll() {

        return hotels;
    }

    @Override
    public void update(Hotel entity) {

        //Opdater hotellet i databasen
        Hotel existingHotel = getById(entity.getId());
        if (existingHotel != null) {
            existingHotel.setName(entity.getName());
            existingHotel.setDescription(entity.getDescription());

        }
    }

    @Override
    public void delete(int id) {
        // fjerner hotellet fra databasen
        Hotel hotelToDelete = getById(id);
        if (hotelToDelete != null) {
            hotels.remove(hotelToDelete);
        }
    }

//Dem her insisterer INtellij at jeg importer?:
//Samme sker i RoomDAO, men jeg har jo implementeret ovenstående. Er det fordi jeg har lavet en interface i stedet for en abstract class? (HAHAHA)

    /*
    @Override
    public void createRoom(Room room) {

    }

    @Override
    public Room updateRoom(Room room) {
        return null;
    }

    @Override
    public void deleteRoom(Room room) {

    }

    @Override
    public List<Room> findRoom(Room room) {
        return null;
    }
}

*/

