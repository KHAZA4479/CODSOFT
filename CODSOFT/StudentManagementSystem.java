import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.InputMismatchException;

public class StudentManagementSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        loadData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    saveData();
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Student Management System");
        System.out.println("1. Add a new student");
        System.out.println("2. Edit student information");
        System.out.println("3. Search for a student");
        System.out.println("4. Display all students");
        System.out.println("5. Save and Exit");
    }

    private static int getChoice() {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
        return choice;
    }

    private static void addStudent() {
        System.out.println("Adding a new student...");
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the grade: ");
        String grade = scanner.nextLine();
        Student student = new Student(name, rollNumber, grade);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    private static void editStudent() {
        System.out.println("Editing student information...");
        System.out.print("Enter the roll number of the student you want to edit: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        Student student = searchStudentByRollNumber(rollNumber);
        if (student != null) {
            System.out.println("Current information of the student:");
            System.out.println(student);
            System.out.println("Enter new information (leave blank to keep unchanged):");
            System.out.print("Enter the new name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                student.setName(name);
            }
            System.out.print("Enter the new grade: ");
            String grade = scanner.nextLine();
            if (!grade.isEmpty()) {
                student.setGrade(grade);
            }
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("No student with that roll number exists.");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter the roll number of the student you want to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();
        Student student = searchStudentByRollNumber(rollNumber);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("No student with that roll number exists.");
        }
    }

    private static Student searchStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    private static void displayStudents() {
        System.out.println("Displaying all students:");
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                students = (ArrayList<Student>) ois.readObject();
                System.out.println("Data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        }
    }

    private static void exit() {
        System.out.println("Thank you for using the Student Management System. Goodbye!");
        scanner.close();
        System.exit(0);
    }
}

class Student implements Serializable {

    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
