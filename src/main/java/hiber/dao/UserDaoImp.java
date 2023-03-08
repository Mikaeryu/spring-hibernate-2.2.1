package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
        TypedQuery<Car> query = sessionFactory.getCurrentSession()
                .createQuery("from Car where model = :model AND series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);

        Optional<Car> carFoundOptional = query.getResultList().stream().findFirst();
        if (!carFoundOptional.isPresent()) {
            System.err.println("There is no car witch such parameters");
            return null;
        }

        Car carFound = carFoundOptional.get();
        long userId = carFound.getId();
        TypedQuery<User> userTypedQuery = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
        userTypedQuery.setParameter("id", userId);

        return userTypedQuery.getSingleResult();
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
