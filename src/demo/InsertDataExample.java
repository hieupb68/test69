package demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import controller.utils.ConnectionUtils;
 
 
public class InsertDataExample {
 
  public static void main(String[] args) throws ClassNotFoundException,
          SQLException {
 
      // Lấy ra kết nối tới cơ sở dữ liệu.
      Connection connection = ConnectionUtils.getMyConnection();
 
      Statement statement = connection.createStatement();
 
      String sql = "Insert into Salary_Grade (Grade, High_Salary, Low_Salary) "
              + " values (3, 40000, 20000) ";
 
      // Thực thi câu lệnh.
      // executeUpdate(String) sử dụng cho các loại lệnh Insert,Update,Delete.
      int rowCount = statement.executeUpdate(sql);
 
      // In ra số dòng được trèn vào bởi câu lệnh trên.
      System.out.println("Row Count affected = " + rowCount);
 
  }
}
