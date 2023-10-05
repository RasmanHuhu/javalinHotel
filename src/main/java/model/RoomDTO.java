package model;

import dao.HotelDAO;
import dao.RoomDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO {

    private int id;

    private int roomNumber;

    private int hotelId;

    private float price;

    private String description;


    //Implement functionality to convert between DTOs and Entities
    public Room convertToEntity(EntityManagerFactory emf) {
        RoomDAO roomDAO = RoomDAO.getInstance(emf);
        HotelDAO hotelDAO = HotelDAO.getInstance(emf);

        Hotel hotel = hotelDAO.findById(this.hotelId);
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel not found");
        }
        Room room = roomDAO.findById(this.id);
        if (room == null) {
            room = new Room();
        }
        room.setRoomNumber(this.roomNumber);
        room.setPrice(this.price);
        room.setDescription(this.description);
        room.setHotel(hotel);

        return room;
        }
    }

