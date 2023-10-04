import config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import model.Hotel;
import model.Room;

public class Main {
    protected static EntityManagerFactory emf;


    public static void main(String[] args) {

        HibernateConfig.addAnnotatedClasses(Room.class, Hotel.class);
        emf = HibernateConfig.getEntityManagerFactoryConfig("hoteldb");

    }
}