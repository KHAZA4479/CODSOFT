import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int rounds = 0;
        int totalAttempts = 0;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You need to guess the number between " + minRange + " and " + maxRange + ".");

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;

            System.out.println("\nRound " + (++rounds));

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                int userGuess = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (userGuess < minRange || userGuess > maxRange) {
                    System.out.println("Please enter a number within the specified range.");
                } else {
                    attempts++;
                    if (userGuess == targetNumber) {
                        System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                        totalAttempts += attempts;
                        totalScore++;
                        break;
                    } else if (userGuess < targetNumber) {
                        System.out.println("Your guess is too low.");
                    } else {
                        System.out.println("Your guess is too high.");
                    }
                }
            }

            if (attempts >= maxAttempts) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + targetNumber + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainChoice = scanner.nextLine().toLowerCase();
            if (!playAgainChoice.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thank you for playing!");
        System.out.println("Total rounds played: " + rounds);
        System.out.println("Total attempts made: " + totalAttempts);
        System.out.println("Total score: " + totalScore);
        scanner.close();
    }
}
