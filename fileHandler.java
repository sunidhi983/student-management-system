
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fileHandler {

    private static final String FILE_NAME = "students.txt";

    public static void saveStudents(List<Student> students) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student student : students) {

                writer.write(
                        student.getId() + "," +
                                student.getName() + "," +
                                student.getAge() + "," +
                                student.getMarks());

                writer.newLine();
            }

            System.out.println("Students saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
public static List<Student> loadStudents() {

    List<Student> students = new ArrayList<>();
    File file = new File(FILE_NAME);

    if (!file.exists()) {
        return students;
    }

    try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            if (data.length != 4) {
                continue;
            }

            int id = Integer.parseInt(data[0]);
            String name = data[1];
            int age = Integer.parseInt(data[2]);
            double marks = Double.parseDouble(data[3]);

            students.add(new Student(id, name, age, marks));
        }

    } catch (IOException | NumberFormatException e) {
        System.out.println("Error loading students: " + e.getMessage());
    }

    return students;
}

}
