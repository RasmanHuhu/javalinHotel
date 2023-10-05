package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

   // @Column(name = "rooms", nullable = true)
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Room> rooms;

    @Column(name = "description", nullable = false)
    private String description;

    public Hotel(String name, String address, List<Room> rooms, String description) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
        this.description = description;
    }
    // Implement functionality to convert between DTOs and Entities
}


