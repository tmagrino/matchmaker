package sql;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SQLReadStudents {

 final static String name = "root";
 final static String password = "root";

 public static void main(String[] args) {
  try {
   String url = "jdbc:mysql://localhost:3306/matchmaker";
   Connection conn = (Connection) DriverManager.getConnection(url, name, password);
   Statement stmt = (Statement) conn.createStatement();
   ResultSet rs;

   rs = stmt.executeQuery("SELECT idstudent,name,gpa FROM Student order by gpa desc");
   while (rs.next()) {
    Long id = rs.getLong("idstudent");
    String nme = rs.getString("Name");
    String gpa = rs.getString("GPA");
    System.out.println(id + ": " + nme + " " + gpa);
   }
   conn.close();
  } catch (Exception e) {
   System.err.println("Got an exception! ");
   System.err.println(e.getMessage());
  }
 }
}
