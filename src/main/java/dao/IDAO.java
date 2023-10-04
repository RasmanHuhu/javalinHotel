package dao;

import model.Hotel;
import model.Room;

import java.util.List;

public abstract class IDAO {

    void Create(Entity entity) {
    }

    Entity GetById(int id) {
        return null;
    }

    void Update(Entitet entitet) {
    }

    public abstract void create(Hotel entity);

    public abstract Hotel getById(int id);

    List<Entity> getAll() {
        return null;
    }

    void Delete(int id) {
    }

    public abstract void update(Hotel entity);

    public abstract void delete(int id);

    public abstract void createRoom(Room room);

    public abstract Room updateRoom(Room room);

    public abstract void deleteRoom(Room room);

    public abstract List<Room> findRoom(Room room);
}