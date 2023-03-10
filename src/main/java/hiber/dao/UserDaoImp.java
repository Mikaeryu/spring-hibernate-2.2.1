package hiber.dao;

import hiber.exceptions.CarNotFoundException;
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

    @Override
    public void add(User user, Car car) {
        user.setCar(car);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        TypedQuery<Car> carQuery = sessionFactory.getCurrentSession()
                .createQuery("from Car where model = :model AND series = :series");
        carQuery.setParameter("model", model);
        carQuery.setParameter("series", series);

        Optional<Car> carFoundOptional = carQuery.getResultList().stream().findFirst();

        Car carFound = carFoundOptional.orElseThrow(() -> new CarNotFoundException("Car not found."));
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

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
}
