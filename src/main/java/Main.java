import config.HibernateConfig;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import model.Hotel;
import model.Room;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Main {

    protected static EntityManagerFactory emf;

    public static void main(String[] args) {

        HibernateConfig.addAnnotatedClasses(Room.class, Hotel.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("hoteldb");

        Javalin app = Javalin.create().start(7007);
        app.routes(getHotelRoutes());
        app.routes(getRoomRoutes());
        //Two resources - Hotel and Room?
        //Vi skal huske at tilføje error handling (app.error(404)? -> ApiException klassen)

    }

    //Vend tilbage, for crud navnene passer ikke
    private static EndpointGroup getHotelRoutes() {
        return () -> {
            path("/hotel", () -> {
                //Get all hotels
                get("/", HotelController.getAllHotels());
                //Get hotel by id
                get("/{id}", HotelController.getHotelById());
                //Create hotel
                post("/", HotelController.createHotel());
                //Update hotel
                put("/{id}", HotelController.updateHotel());
                //Delete hotel
                delete("/{id}", HotelController.deleteHotel());
            });

        };
    }

    //Vend tilbage, for crud navnene passer ikke
    private static EndpointGroup getRoomRoutes() {
        return () -> {
            path("/hotel/room", () -> {
                //Get all rooms
                get("/", RoomController.getAllRooms());
                //Get room by id
                get("/:id", RoomController.getRoomById());
                //Create room
                post("/", RoomController.createRoom());
                //Update room
                put("/{id}", RoomController.updateRoom());
                //Delete room
                delete("/{id}", RoomController.deleteRoom());
            });

    };
}
}