import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome! Choose an option: ");
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        Integer userId = Authentication.authenticate(username, password);

        switch (choice) {
            case 1:
                User user = new User(username, password);
                boolean registered = Authentication.registerUser(user);
                if (registered) {
                    System.out.println("Registration successful!");
                    startApp();
                } else {
                    System.out.println("Registration failed.");
                    startApp();
                }
            case 2:
                boolean authenticated = Authentication.authenticateUser(username, password);
                if (authenticated) {
                    System.out.println("Login successful!");
                    MentalHealthJournal.run(userId);
                } else {
                    System.out.println("Invalid username or password.");
                    startApp();
                }
        }
        scanner.close();
    }
}
