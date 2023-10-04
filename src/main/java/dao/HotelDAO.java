package dao;

import dao.IDAO;
import jakarta.persistence.EntityManagerFactory;
import model.Hotel;

import java.util.List;

public class HotelDAO extends IDAO {

    public static HotelDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonDAOImpl();

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
            // Update other properties as needed.
        }
    }

        @Override
        public void delete ( int id){
            // fjerner hotellet fra databasen
            Hotel hotelToDelete = getById(id);
            if (hotelToDelete != null) {
                hotels.remove(hotelToDelete);
            }
        }
    }
