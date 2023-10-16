import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int numberOfRounds = 3; // You can change this as needed
        int maxAttempts = 7; // You can change this as needed
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("You have " + numberOfRounds + " rounds to play. Let's get started!");

        for (int round = 1; round <= numberOfRounds; round++) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nRound " + round + " - Guess the number between " + lowerBound + " and " + upperBound);

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    hasGuessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    int roundScore = maxAttempts - attempts + 1;
                    totalScore += roundScore;
                    System.out.println("Round Score: " + roundScore);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }

                if (attempts == maxAttempts) {
                    System.out.println("You've run out of attempts. The correct number was " + targetNumber);
                }
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Total Score: " + totalScore);
        scanner.close();
    }
}
