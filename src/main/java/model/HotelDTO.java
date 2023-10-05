package model;

import dao.HotelDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class HotelDTO {

    private int id;

    private String name;

    private String address;

    private List<RoomDTO> rooms;

    private String description;


    //Implement functionality to convert between DTOs and Entities
    public Hotel convertToEntity(EntityManagerFactory emf) {

        HotelDAO hotelDAO = HotelDAO.getInstance(emf);
        Hotel hotel = hotelDAO.findById(this.id);
        if (hotel == null) {
            hotel = new Hotel();
            hotel.setAddress(this.address);
            hotel.setName(this.name);
            hotel.setDescription(this.description);
        }
        if (rooms == null) {
            hotel.setRooms(new ArrayList<>());
            return hotel;
        }
        List<Room> rooms = this.rooms.stream().map((room) -> room.convertToEntity(emf)).toList();
        hotel.setRooms(rooms);
        return hotel;
    }
}




