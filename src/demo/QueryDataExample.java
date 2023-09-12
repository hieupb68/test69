package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.utils.ConnectionUtils;
 
 
public class QueryDataExample {
 
  public static void main(String[] args) throws ClassNotFoundException,
          SQLException {
 
      // Lấy ra đối tượng Connection kết nối vào DB.
      Connection connection = ConnectionUtils.getMyConnection();
 
      // Tạo đối tượng Statement.
      Statement statement = connection.createStatement();
 
      String sql = "Select Emp_Id, Emp_No, Emp_Name from Employee";
 
      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
      ResultSet rs = statement.executeQuery(sql);

      // Duyệt trên kết quả trả về.
      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
          int empId = rs.getInt(1);
          String empNo = rs.getString(2);
          String empName = rs.getString("Emp_Name");
          System.out.println("--------------------");
          System.out.println("EmpId:" + empId);
          System.out.println("EmpNo:" + empNo);
          System.out.println("EmpName:" + empName);
      }
      // Đóng kết nối
      connection.close();
  }
 
}