package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.HotelDAO;
import dao.RoomDAO;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.Hotel;
import model.HotelDTO;
import model.Room;
import model.RoomDTO;

public class RoomController {
    private Gson gson;
    private RoomDAO roomDAO;
    private HotelDAO hotelDAO;


    public RoomController(EntityManagerFactory emf) {
        this.roomDAO = RoomDAO.getInstance(emf);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.hotelDAO = HotelDAO.getInstance(emf);
    }
    public Handler getAllRooms() {
        return ctx -> {
            ctx.json(gson.toJson(roomDAO.getAll()));
        };
    }

    public Handler getRoomById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(gson.toJson(roomDAO.findById(id)));
        };
    }

    public Handler createRoom() {
        return ctx -> {
            RoomDTO roomDTO = gson.fromJson(ctx.body(), RoomDTO.class);
            EntityManager em = RoomDAO.getEmf().createEntityManager();
            Hotel hotel = em.find(Hotel.class, roomDTO.getHotelId());
            if (hotel == null) {
                em.close(); // Husk at lukke EntityManager
                throw new IllegalArgumentException("Hotel not found");
            }

            Room room = roomDTO.convertToEntity(RoomDAO.getEmf());
            room.setHotel(hotel); // her tilføjer vi hotel til room

            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
            em.close();

            ctx.status(201);
        };
    }


  /* public Handler createRoom() {
        return ctx -> {
            Room room = gson.fromJson(ctx.body(), RoomDTO.class).convertToEntity(RoomDAO.getEmf());
            roomDAO.create(room);
            ctx.status(201);
        };
    }*/

    public Handler updateRoom() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Room room = gson.fromJson(ctx.body(), RoomDTO.class).convertToEntity(RoomDAO.getEmf());
            room.setId(id);
            roomDAO.update(room);
            ctx.status(204);
        };
    }

    public Handler deleteRoom() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Room room = roomDAO.findById(id);
            roomDAO.delete(room);
            ctx.status(204);
        };
    }
}
