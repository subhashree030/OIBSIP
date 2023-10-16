import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Train {
    private String trainNumber;
    private String trainName;

    public Train(String trainNumber, String trainName) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }
}

class User {
    private String username;
    private String password;
    private String trainNumber;

    public User(String username, String password, String trainNumber) {
        this.username = username;
        this.password = password;
        this.trainNumber = trainNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}

class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(String name, String trainNumber, String classType, String dateOfJourney, String from, String to) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    // Getters and setters here

    @Override
    public String toString() {
        return "Reservation{" +
                "name='" + name + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                ", classType='" + classType + '\'' +
                ", dateOfJourney='" + dateOfJourney + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
public class ReservationSystem {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Train> trains = new HashMap<>();
    private Map<String, Reservation> reservations = new HashMap<>();
    private int pnrCounter = 1;

    public void registerUser(String username, String password, String trainNumber) {
        users.put(username, new User(username, password, trainNumber));
        System.out.println("User registered successfully.");
    }
    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void addTrain(String trainNumber, String trainName) {
        trains.put(trainNumber, new Train(trainNumber, trainName));
        System.out.println("Train added successfully.");
    }

    public void makeReservation(String username, String classType, String dateOfJourney, String from, String to) {
        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found. Please register or log in.");
            return;
        }
    
        String trainNumber = user.getTrainNumber();
        Train train = trains.get(trainNumber);
        if (train == null) {
            System.out.println("Train not found. Please enter a valid train number.");
            return;
        }
    
        String pnr = "PNR" + pnrCounter++;
        Reservation reservation = new Reservation(user.getUsername(), trainNumber, classType, dateOfJourney, from, to);
        reservations.put(pnr, reservation);
        System.out.println("Reservation created with PNR: " + pnr);
        System.out.println("Train Name: " + train.getTrainName()); // Display train name
    }
    

    public void cancelReservation(String pnr) {
        Reservation reservation = reservations.get(pnr);
        if (reservation == null) {
            System.out.println("Reservation not found with PNR: " + pnr);
            return;
        }

        System.out.println("Reservation to cancel: " + reservation);
        System.out.print("Press 'OK' to confirm cancellation: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("OK")) {
            reservations.remove(pnr);
            System.out.println("Reservation with PNR " + pnr + " has been cancelled.");
        } else {
            System.out.println("Cancellation not confirmed.");
        }
    }
 public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);
        String currentUsername = null;
        while (true) {
            if (currentUsername == null) {
                System.out.println("\nMenu:");
                System.out.println("1. Register User");
                System.out.println("2. Log In");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();
                        System.out.print("Enter Train Number: ");
                        String trainNumber = scanner.nextLine();
                        reservationSystem.registerUser(username, password, trainNumber);
                        break;
                    case 2:
                        System.out.print("Enter Username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        String loginPassword = scanner.nextLine();
                        User user = reservationSystem.login(loginUsername, loginPassword);
                        if (user != null) {
                            currentUsername = user.getUsername();  // Set the current username
                            System.out.println("Login successful.");
                        } else {
                            System.out.println("Login failed. Invalid credentials.");
                        }
                        break;
                        case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                // User is logged in
                System.out.println("\nMenu:");
                System.out.println("1. Add Train");
                System.out.println("2. Make Reservation");
                System.out.println("3. Cancel Reservation");
                System.out.println("4. Log Out");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter Train Number: ");
                        String trainNumber = scanner.nextLine();
                        System.out.print("Enter Train Name: ");
                        String trainName = scanner.nextLine();
                        reservationSystem.addTrain(trainNumber, trainName);
                        break;
                    case 2:
                        System.out.print("Enter Username: ");
                        String reservationUsername = scanner.nextLine();
                        
                        User user = reservationSystem.users.get(reservationUsername);
                        if (user != null) {
                            String userTrainNumber = user.getTrainNumber();
                            String userTrainName = reservationSystem.trains.get(userTrainNumber).getTrainName();
                            System.out.println("Train Number: " + userTrainNumber);
                            System.out.println("Train Name: " + userTrainName);
                            
                            System.out.print("Enter Class Type: ");
                            String classType = scanner.nextLine();
                            System.out.print("Enter Date of Journey: ");
                            String dateOfJourney = scanner.nextLine();
                            System.out.print("Enter From: ");
                            String from = scanner.nextLine();
                            System.out.print("Enter To: ");
                            String to = scanner.nextLine();
                            
                            reservationSystem.makeReservation(reservationUsername, classType, dateOfJourney, from, to);
                        } else {
                            System.out.println("User not found. Please register or log in.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter PNR to cancel: ");
                        String pnr = scanner.nextLine();
                        reservationSystem.cancelReservation(pnr);
                        break;
                    case 4:
                        // Log Out
                        System.out.print("Are you sure you want to log out? (yes/no): ");
                        String confirmation = scanner.nextLine().trim().toLowerCase();
                        if (confirmation.equals("yes")) {
                            currentUsername = null;  // Log out the user
                            System.out.println("Logged out successfully.");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
