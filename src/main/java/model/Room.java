package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private int roomNumber;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "description", nullable = false)
    private String description;

    public Room(int roomNumber, Hotel hotel, float price, String description) {
        this.roomNumber = roomNumber;
        this.hotel = hotel;
        this.price = price;
        this.description = description;
    }
}

    // Implement functionality to convert between DTOs and Entities
    // json -> gson?
