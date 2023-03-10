package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    //Добавил перегруженный метод для добавления юзера вместе с с машиной
    @Override
    public void add(User user, Car car) {
        user.setCar(car);
        sessionFactory.getCurrentSession().save(user);
    }

    //метод для поиска юзера по машине
    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        TypedQuery<Car> carQuery = sessionFactory.getCurrentSession()
                .createQuery("from Car where model = :model AND series = :series");
        carQuery.setParameter("model", model);
        carQuery.setParameter("series", series);

        Optional<Car> carFoundOptional = carQuery.getResultList().stream().findFirst();
        if (!carFoundOptional.isPresent()) {
            System.err.println("There is no car witch such parameters");
            return null;
        } //вот тут вообще не уверен насчёт выкидывание null, это нормально, так делать? просто это рядовой запрос на поиск

        Car carFound = carFoundOptional.get();
        long userId = carFound.getId();
        TypedQuery<User> userQuery = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
        userQuery.setParameter("id", userId);

        return userQuery.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    //добавил метод, возрвращающий список машин
    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
}
