package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.HotelDAO;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.Hotel;
import model.HotelDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HotelController {

    private Gson gson;

    private HotelDAO hotelDAO;
    private EntityManagerFactory emf;


    public HotelController(EntityManagerFactory emf) {
        this.emf = emf;
        this.hotelDAO = HotelDAO.getInstance(emf);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }


    public Handler getAllHotels() {
        return ctx -> {
            try (EntityManager em = emf.createEntityManager()) {
                TypedQuery<Hotel> query = em.createQuery("SELECT DISTINCT h FROM Hotel h LEFT JOIN FETCH h.rooms", Hotel.class);
                List<Hotel> hotels = query.getResultList();
                ctx.json(hotels); // Send hoteller som JSON-svar
            }
        };
    }


  /*  public Handler getAllHotels() {
        return ctx -> {
            ctx.json(gson.toJson(hotelDAO.getAll()));
        };
    }*/


    public Handler getHotelById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.findById(id);
            if (hotel != null) {
                hotel.getRooms().size();
                ctx.json(gson.toJson(hotel));
            } else {

                ctx.status(404).result("Hotel not found");
            }
        };
    }

/*    public Handler getHotelById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(gson.toJson(hotelDAO.findById(id)));
        };
    }*/

    public Handler getRoomsById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(gson.toJson(hotelDAO.findById(id).getRooms()));
        };
        }

    public Handler createHotel() {
        return ctx -> {
            Hotel hotel = gson.fromJson(ctx.body(), HotelDTO.class).convertToEntity(HotelDAO.getEmf());
            hotelDAO.create(hotel);
            ctx.status(201);
        };
    }

    public Handler updateHotel() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = gson.fromJson(ctx.body(), HotelDTO.class).convertToEntity(HotelDAO.getEmf());
            hotel.setId(id);
            hotelDAO.update(hotel);
            ctx.status(204);
        };
    }

    public Handler deleteHotel() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hotelDAO.findById(id);
            hotelDAO.delete(hotel);
            ctx.status(204);
        };
    }
}
