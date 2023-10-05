package dao;

import java.util.List;

public interface IDAO<T> {


    void create(T obj);

    void update(T obj);

    void delete(T obj);

    T findById(int id);

    List<T> getAll();
}
