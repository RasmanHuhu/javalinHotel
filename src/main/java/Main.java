import config.HibernateConfig;
import controller.HotelController;
import controller.RoomController;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import model.Hotel;
import model.Room;

import static io.javalin.apibuilder.ApiBuilder.*;


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
        HotelController hotelController = new HotelController(emf);
        return () -> {
            path("/hotel", () -> {
                //Get all hotels
                get("/", hotelController.getAllHotels());
                //Get hotel by id
                get("/{id}", hotelController.getHotelById());
                //Get rooms by hotel id
                get("/{id}/rooms", hotelController.getRoomsById());
                //Create hotel
                post("/", hotelController.createHotel());
                //Update hotel
                put("/{id}", hotelController.updateHotel());
                //Delete hotel
                delete("/{id}", hotelController.deleteHotel());
            });

        };
    }

    //Vend tilbage, for crud navnene passer ikke
    private static EndpointGroup getRoomRoutes() {
        RoomController roomController = new RoomController(emf);
        return () -> {
            path("/room", () -> {
                //Get all rooms
                get("/", roomController.getAllRooms());
                //Get room by id
                get("/{id}", roomController.getRoomById());
                //Create room
                post("/", roomController.createRoom());
                //Update room
                put("/{id}", roomController.updateRoom());
                //Delete room
                delete("/{id}", roomController.deleteRoom());
            });

        };
    }
}