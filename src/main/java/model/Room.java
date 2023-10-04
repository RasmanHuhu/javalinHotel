package model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "description", nullable = false)
    private String description;

    public Room(String roomNumber, int hotelId, int price, String description) {
        this.roomNumber = roomNumber;
        this.hotelId = hotelId;
        this.price = price;
        this.description = description;
    }

    // Implement functionality to convert between DTOs and Entities
    // json -> gson?
}
