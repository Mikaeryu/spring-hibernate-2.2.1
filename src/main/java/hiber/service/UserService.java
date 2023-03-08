package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import java.util.List;

public interface UserService extends UserDao {
    @Override
    void add(User user);

    @Override
    void add(User user, Car car);

    @Override
    List<User> listUsers();

    @Override
    List<Car> listCars();

    @Override
    User getUserByCar(String model, int series);
}
