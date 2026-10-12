import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentService();

        int choice=0;

        do {
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Calculate Grade");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                continue;
            }
            if (choice < 1 || choice > 7) {
                System.out.println("Invalid menu choice");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter name: ");
                    String name = sc.next();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                    System.out.print("Enter marks: ");
                    double marks = sc.nextDouble();

                    Student student = new Student(id, name, age, marks);
                    service.addStudent(student);
                    break;

                case 2:
                    service.viewStudents();
                    break;

                case 3:
                    System.out.println("Enter student id to search: ");
                    int searchID = sc.nextInt();
                    service.searchStudent(searchID);
                    break;
                case 4:
                    System.out.println("Enter id: ");
                    id = sc.nextInt();
                    System.out.println("Enter name: ");
                    name = sc.next();
                    System.out.print("Enter age: ");
                    age = sc.nextInt();
                    System.out.print("Enter marks: ");
                    marks = sc.nextDouble();
                    service.updateStudent(id, name, age, marks);
                    break;
                case 5:
                    System.out.println("Enter id: ");
                    id = sc.nextInt();
                    if (service.deleteStudent(id))
                        System.out.println("Student deleted successfuly");
                    else
                        System.out.println("Student of id " + id + " not found.");
                    break;
                case 6:
                    System.out.print("Enter student ID: ");
                    int gradeId = sc.nextInt();

                    Student gradeStudent = service.searchStudentById(gradeId);

                    if (gradeStudent != null) {
                        System.out.println("Student: " + gradeStudent.getName());
                        System.out.println("Marks: " + gradeStudent.getMarks());
                        System.out.println("Grade: " + gradeStudent.geGrade());
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 7:
                    System.out.println("Thank you!");
            }
        } while (choice != 7);
        sc.close();
    }

}