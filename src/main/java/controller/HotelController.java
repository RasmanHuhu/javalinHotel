package controller;

import io.javalin.http.Handler;
import model.Hotel;

public class HotelController {


    //Skal vi have et .check?

    public Handler getAllHotels = ctx -> {
        ctx.json(HotelDAO.getAllHotels());
    };
}

@Override
public Handler create() {
    return ctx -> {
        Hotel hotel = ctx.bodyAsClass(Hotel.class);
        HotelDAO.create(hotel);
        ctx.status(201);
    };
}
}
