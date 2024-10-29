package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Tom", "Smith", (byte) 15);
        userService.saveUser("Bob", "Gauss", (byte) 16);
        userService.saveUser("Mike", "End", (byte) 17);
        userService.saveUser("Jim", "Anything", (byte) 18);
        List<User> users = userService.getAllUsers();
        users.stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
