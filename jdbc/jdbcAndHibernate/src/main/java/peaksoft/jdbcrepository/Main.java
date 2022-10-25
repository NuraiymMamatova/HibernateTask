package peaksoft.jdbcrepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Scanner in = new Scanner(System.in);
        String button = "null";
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        try {
            while (!button.equals("x")) {
                commands();
                button = in.nextLine();
                switch (button) {
                    case "1" -> userDaoJdbc.createUsersTable();
                    case "2" -> {
                        System.out.println("Return name, last name and age: ");
                        userDaoJdbc.saveUser(in.nextLine(), in.nextLine(), in.nextByte());
                    }
                    case "3" -> userDaoJdbc.getAllUsers();
                    case "4" -> {
                        System.out.println("Return id user for remove");
                        userDaoJdbc.removeUserById(in.nextByte());
                    }
                    case "5" -> userDaoJdbc.cleanUsersTable();
                    case "6" -> userDaoJdbc.dropUsersTable();
                    default -> System.out.println("Oops!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void commands() {
        System.out.println("""
                Press 1 create users table
                Press 2 insert user
                Press 3 get all users
                Press 4 remove by id
                Press 5 clean users table
                Press 6 drop users table
                Press x stop while
                """);
    }
}
