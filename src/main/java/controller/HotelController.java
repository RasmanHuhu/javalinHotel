package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.HotelDAO;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import model.Hotel;
import model.HotelDTO;

public class HotelController {

    private Gson gson;

    private HotelDAO hotelDAO;


    public HotelController(EntityManagerFactory emf) {
        this.hotelDAO = HotelDAO.getInstance(emf);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }



    public Handler getAllHotels() {
        return ctx -> {
            ctx.json(gson.toJson(hotelDAO.getAll()));
        };
    }

    public Handler getHotelById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(gson.toJson(hotelDAO.findById(id)));
        };
    }

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
