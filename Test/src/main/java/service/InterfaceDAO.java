package service;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<E> {

    void insert(E e) throws SQLException;

    E select(int id);

    List<E> selectAll();

    boolean delete(int id) throws SQLException;

    boolean update(E e) throws SQLException;
}
