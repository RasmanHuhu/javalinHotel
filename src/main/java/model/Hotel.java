package model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "rooms", nullable = false)
    private int rooms;

    @Column(name = "description", nullable = false)
    private String description;

    public Hotel(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
