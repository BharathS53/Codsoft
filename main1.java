import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class main1{
    private static StudentManagementSystem sms = new StudentManagementSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    displayAllStudents();
                    break;
                case 5:
                    saveStudents();
                    break;
                case 6:
                    loadStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Search Student");
        System.out.println("4. Display All Students");
        System.out.println("5. Save Students to File");
        System.out.println("6. Load Students from File");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        if (name.isEmpty() || grade.isEmpty() || rollNumber <= 0) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        Student student = new Student(name, rollNumber, grade);
        sms.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void editStudent() {
        System.out.print("Enter roll number of student to edit: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = sms.searchStudent(rollNumber);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        System.out.print("Enter new grade (leave blank to keep current): ");
        String grade = scanner.nextLine();

        if (!name.isEmpty()) {
            student.setName(name);
        }
        if (!grade.isEmpty()) {
            student.setGrade(grade);
        }

        System.out.println("Student information updated successfully.");
    }

    private static void searchStudent() {
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = sms.searchStudent(rollNumber);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(student);
        }
    }

    private static void displayAllStudents() {
        sms.displayAllStudents();
    }

    private static void saveStudents() {
        System.out.print("Enter filename to save: ");
        String filename = scanner.nextLine();
        sms.saveToFile(filename);
        System.out.println("Students saved to file successfully.");
    }

    private static void loadStudents() {
        System.out.print("Enter filename to load: ");
        String filename = scanner.nextLine();
        sms.loadFromFile(filename);
        System.out.println("Students loaded from file successfully.");
    }
}