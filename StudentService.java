import java.util.ArrayList;
public class StudentService {
    private ArrayList<Student> students;

    // constructor
    public StudentService() {
        students = new ArrayList<>(fileHandler.loadStudents());
    }

    // check for duplicate id
    public Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }
        return null;    

    }

    // Add student
    public void addStudent(Student s) {

        if (s.getAge() <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        if (!isValidMarks(s.getMarks())) {
            System.out.println("Marks must be between 0 and 100!");
            return;
        }
        if (searchStudentById(s.getId()) != null) {
            System.out.println("Error: Student ID already existed!");
            return;
        }
        students.add(s);
        saveToFile();
        System.out.println("Student added succcessfully!");
    }

    // View students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Serach student
    public void searchStudent(int id) {
        Student tempStudent = searchStudentById(id);
        if (tempStudent != null) {
            System.out.println(tempStudent);
            return;
        }
        System.out.println("Student not found.");
    }

    // Update student
    public void updateStudent(int id, String name, int age, double marks) {
         if (age <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        if (!isValidMarks(marks)) {
            System.out.println("Marks must be between 0 and 100!");
            return;
        }
       
        Student tempStudent = searchStudentById(id);
        if (tempStudent != null) {
            tempStudent.setAge(age);
            tempStudent.setName(name);
            tempStudent.setMarks(marks);
            System.out.println("Student updated successfully!");
            saveToFile();
            return;
        }
        System.out.println("Student not found.");
    }

    // Delete student
    public boolean deleteStudent(int id) {
        Student tempStudent = searchStudentById(id);
        if (tempStudent != null) {
            students.remove(tempStudent);
            saveToFile();
            return true;
        }
        return false;
    }

    // marks must be between 0 and 100
    private boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

    // file me data save karna h
    private void saveToFile() {
        fileHandler.saveStudents(students);
    }
}
