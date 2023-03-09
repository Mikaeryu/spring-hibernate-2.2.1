package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import java.util.List;

//сделал UserService расширяющим UserDao
public interface UserService extends UserDao {
    @Override
    void add(User user);

    //добавил перегруженный метод
    @Override
    void add(User user, Car car);

    @Override
    List<User> listUsers();

    //добавил метод со списком авто
    @Override
    List<Car> listCars();

    //метод для поиска юзера по авто
    @Override
    User getUserByCar(String model, int series);
}
