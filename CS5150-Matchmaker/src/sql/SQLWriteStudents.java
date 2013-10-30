package sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import model.GetStudentInfo;
import model.Student;

public class SQLWriteStudents {

    final static String name = "root";
    final static String password = "root";
    final static String tablename = "Student";

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/matchmaker";
        Connection conn = (Connection) DriverManager.getConnection(url, name, password);
        Statement stmt = (Statement) conn.createStatement();

        String sql = "show tables like '" + tablename + "'";
        ResultSet rs;
        rs = stmt.executeQuery(sql);
        if (!rs.first()) { //create the table
            sql = "CREATE TABLE " + tablename + "(studentid BIGINT NOT NULL AUTO_INCREMENT, Name VARCHAR(254), Phone VARCHAR(20), PRIMARY KEY (studentid))";
            stmt.executeUpdate(sql);
        }

        List<Student> mylist = GetStudentInfo.get();

        int rows = 0;
        for (Student st : mylist) {
            sql = "INSERT INTO " + tablename
                    + "(Name, GPA)"
                    + "VALUES"
                    + "('" + st.getName() + "','" + st.getGpa() + "')";

            rows += stmt.executeUpdate(sql);
        }

        System.out.println("Added " + rows + " students.");
    }
}