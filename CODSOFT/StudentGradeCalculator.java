import java.util.Scanner;

public class StudentGradeCalculator {

    public static final int NUM_SUBJECTS = 5;
    public static final int A_MIN = 90;
    public static final int B_MIN = 80;
    public static final int C_MIN = 70;
    public static final int D_MIN = 60;
    public static Scanner scanner = new Scanner(System.in);

    public static int[] getMarks() {
        int[] marks = new int[NUM_SUBJECTS];
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.print("Enter the marks obtained in subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }
        return marks;
    }

    public static int getTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public static double getAveragePercentage(int total) {
        double average = (double) total / NUM_SUBJECTS;
        return average;
    }

    public static char getGrade(double average) {
        char grade;
        if (average >= A_MIN) {
            grade = 'A';
        } else if (average >= B_MIN) {
            grade = 'B';
        } else if (average >= C_MIN) {
            grade = 'C';
        } else if (average >= D_MIN) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        return grade;
    }

    public static void displayResults(int total, double average, char grade) {
        System.out.println("Your total marks are: " + total);
        System.out.println("Your average percentage is: " + average);
        System.out.println("Your corresponding grade is: " + grade);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Student Grade Calculator!");
        System.out.println("Please enter your marks obtained (out of 100) in each subject.");
        int[] marks = getMarks();
        int total = getTotalMarks(marks);
        double average = getAveragePercentage(total);
        char grade = getGrade(average);
        displayResults(total, average, grade);
        System.out.println("Thank you for using the Student Grade Calculator. Goodbye!");
    }
}
