package model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "hotel", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "description", nullable = false)
    private String description;

    public Room(String roomNumber, Hotel hotel, float price, String description) {
        this.roomNumber = roomNumber;
        this.hotel = hotel;
        this.price = price;
        this.description = description;
    }
}

    // Implement functionality to convert between DTOs and Entities
    // json -> gson?
