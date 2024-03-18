package pl.clockworkjava.advanced.jpa;

import pl.clockworkjava.advanced.jpa.domain.Student;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        createTable();

        Student kacper = new Student(1, "Kacper");
        Student mirek = new Student(2, "Mirek");

        insertStudent(kacper);
        insertStudent(mirek);

        List<Student> students = getStudents();

        students.forEach( student -> {
            System.out.println(student.toString());
        });
    }

    private static void createTable() throws SQLException, ClassNotFoundException {
        Connection conn = H2Config.getDBConnection();
        Statement statement = conn.createStatement();
        String query = "CREATE TABLE STUDENT(id int primary key, name varchar(255))";
        statement.execute(query);
        conn.commit();
    }

    private static void insertStudent(Student student) throws SQLException, ClassNotFoundException {
        Connection connection = H2Config.getDBConnection();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO STUDENT VALUES(" + student.getId() +",\'"+student.getName()+"\')";
        statement.execute(query);
        connection.commit();
    }

    public static List<Student> getStudents() throws SQLException, ClassNotFoundException {
        List<Student> students = new ArrayList<Student>();
        Connection conn = H2Config.getDBConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT");

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            students.add(new Student(id, name));
        }
        return students;
    }

}
