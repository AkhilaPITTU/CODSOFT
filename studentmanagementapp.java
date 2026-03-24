import java.util.*;
import java.io.*;

// Student Class
class Student implements Serializable {

    String name;
    int rollNumber;
    String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public void displayStudent() {
        System.out.println("Name: " + name + " | Roll Number: " + rollNumber + " | Grade: " + grade);
    }
}


// Student Management System Class
class StudentManagementSystem {

    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void removeStudent(int rollNumber) {

        Student found = null;

        for (Student s : students) {
            if (s.rollNumber == rollNumber) {
                found = s;
                break;
            }
        }

        if (found != null) {
            students.remove(found);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void searchStudent(int rollNumber) {

        for (Student s : students) {
            if (s.rollNumber == rollNumber) {
                System.out.println("Student found:");
                s.displayStudent();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n--- Student List ---");

        for (Student s : students) {
            s.displayStudent();
        }
    }

    // Save students to file
    public void saveToFile() {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(students);
            System.out.println("Student data saved.");

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load students from file
    @SuppressWarnings("unchecked")
    public void loadFromFile() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            students = (List<Student>) in.readObject();

        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}


// Main Class
public class StudentManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        sms.loadFromFile();

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter roll number: ");
                    int roll = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Invalid input!");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    break;

                case 2:

                    System.out.print("Enter roll number to remove: ");
                    int removeRoll = sc.nextInt();

                    sms.removeStudent(removeRoll);
                    break;

                case 3:

                    System.out.print("Enter roll number to search: ");
                    int searchRoll = sc.nextInt();

                    sms.searchStudent(searchRoll);
                    break;

                case 4:

                    sms.displayAllStudents();
                    break;

                case 5:

                    sms.saveToFile();
                    System.out.println("Exiting system...");
                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}