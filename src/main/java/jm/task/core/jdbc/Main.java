package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Лили", "Эванс", (byte) 21);
        userService.saveUser("Гарри", "Поттер", (byte) 1);
        userService.saveUser("Том", "Риддл", (byte) 53);
        userService.saveUser("Альбус", "Дамблдор", (byte) 99);

        List<User> users = userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
