package controller;

import io.javalin.http.Handler;
import model.Room;

public class RoomController {

    public Handler getRoomById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Room room = Room.getRoomById(id);
        ctx.json(room);
    };
}
