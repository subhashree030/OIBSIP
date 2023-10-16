import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String text;
    private List<String> options;
    private int correctOption;

    public Question(String text, List<String> options, int correctOption) {
        this.text = text;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateProfile(String newUsername) {
        this.username = newUsername;
    }
}

public class OnlineExamSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some sample questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is 2 + 2?", List.of("3", "4", "5"), 2));  // Correct answer is option 2.
        questions.add(new Question("What is the capital of France?", List.of("Berlin", "Madrid", "Paris"), 3)); // Correct answer is option 3.

        // User login
        System.out.println("Welcome to the Online Exam System!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);

        // Validate the user
        if (isValidUser(user)) {
            System.out.println("Login successful! Welcome, " + user.getUsername() + "!\n");

            boolean continueSession = true;

            while (continueSession) {
                // Offer profile and password update
                System.out.println("1. Update Profile");
                System.out.println("2. Change Password");
                System.out.println("3. Start Exam");
                System.out.println("4. Close Session and Logout");
                System.out.print("Select an option (1/2/3/4): ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Enter your new username: ");
                        String newUsername = scanner.next();
                        user.updateProfile(newUsername);
                        System.out.println("Profile updated successfully!\n");
                        break;
                    case 2:
                        System.out.print("Enter your new password: ");
                        String newPassword = scanner.next();
                        user.setPassword(newPassword);
                        System.out.println("Password changed successfully!\n");
                        break;
                    case 3:
                        // Start the exam
                        int score = conductExam(questions, scanner);

                        // Display the result
                        System.out.println("Exam completed! Your score: " + score + "/" + questions.size() + "\n");
                        break;
                    case 4:
                        continueSession = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please select 1, 2, 3, or 4.\n");
                }
            }

            // Close the session and logout
            System.out.println("Closing session and logging out... Goodbye, " + user.getUsername() + "!");
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }

        scanner.close();
    }

    // Simulate user validation (replace with actual validation logic)
    private static boolean isValidUser(User user) {
        // In this example, we're allowing any username/password combination to be valid.
        return true;
    }

    // Conduct the exam with a timer and auto-submit
    private static int conductExam(List<Question> questions, Scanner scanner) {
        int score = 0;
        final int timeLimitInMinutes = 10; // Set the time limit in minutes

        // Create a timer to track time remaining
        Timer timer = new Timer();
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (timeLimitInMinutes * 60 * 1000);

        // Schedule a task to auto-submit when the time is up
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Submitting your answers...");
                timer.cancel();
            }
        }, timeLimitInMinutes * 60 * 1000);

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            // Display the question
            System.out.println("Question " + (i + 1) + ": " + question.getText());
            for (int j = 0; j < question.getOptions().size(); j++) {
                System.out.println((j + 1) + ". " + question.getOptions().get(j));
            }

            // Get the user's answer
            System.out.print("Select your answer (1-" + question.getOptions().size() + "): ");
            int userAnswer = scanner.nextInt();

            // Check if the answer is correct
            if (userAnswer == question.getCorrectOption()) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getOptions().get(question.getCorrectOption() - 1) + "\n");
            }

            // Check if time is up
            if (System.currentTimeMillis() >= endTime) {
                System.out.println("Time's up! Submitting your answers...");
                break;
            }
        }

        // Cancel the timer
        timer.cancel();

        return score;
    }
}
