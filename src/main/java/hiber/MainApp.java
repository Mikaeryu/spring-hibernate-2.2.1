package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class);
//
//        UserService userService = context.getBean(UserService.class);
//
//        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
//        List<User> users = userService.listUsers();
//        for (User user : users) {
//            System.out.println("Id = " + user.getId());
//            System.out.println("First Name = " + user.getFirstName());
//            System.out.println("Last Name = " + user.getLastName());
//            System.out.println("Email = " + user.getEmail());
//            System.out.println();
//        }
//
//        context.close();

        AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("Car1", 1111);
        Car car2 = new Car("Car2", 2222);
        Car car3 = new Car("Car3", 3333);
        Car car4 = new Car("Car4", 4444);

        userService.add(user1, car1);
        userService.add(user2, car2);
        userService.add(user3, car3);
        userService.add(user4, car4);

        List<User> users = userService.listUsers();
        users.forEach(System.out::println);

        List<Car> cars = userService.listCars();
        cars.forEach(System.out::println);

        User foundUser = userService.getUserByCar("Car3", 3333);
        System.out.println("FOUND USER BY CAR: " + foundUser);

        context.close();
    }
}
