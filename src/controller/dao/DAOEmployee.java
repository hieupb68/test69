package controller.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import model.Employee;

public class DAOEmployee extends IDAO<Employee> {
	public DAOEmployee(Connection conn) {
		this.conn = conn;
		try {
			this.statement = this.conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Employee[] selectAll() {
		Vector<Employee> ee = new Vector<Employee>();
		Employee[] result;
		try {
			String sql = "Select * from Employee";

			rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				Employee e = new Employee(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getDate(4),
						rs.getBytes(5),
						rs.getString(6),
						rs.getFloat(7),
						rs.getInt(8),
						BigInteger.valueOf(rs.getInt(9)));
				ee.add(e);

				i++;
			}
			result = new Employee[i];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return ee.toArray(result);
	}

	@Override
	public Employee[] selectByName(String name) {
		Vector<Employee> ee = new Vector<Employee>();
		Employee[] result;
		try {
			String sql = "Select * from Employee where Emp_Name='" + name+"'" ;

			rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				Employee e = new Employee(
						rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getDate(4),
						rs.getBytes(5),
						rs.getString(6),
						rs.getFloat(7),
						rs.getInt(8),
						BigInteger.valueOf(rs.getInt(9)));
				ee.add(e);

				i++;
			}
			result = new Employee[i];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return ee.toArray(result);
	}

	@Override
	public int insert(Employee e) {
		String sql = "INSERT INTO EMPLOYEE (EMP_ID,"+
				"EMP_NAME,"+
				"EMP_NO,"+
				"HIRE_DATE,"+
				"IMAGE,"+
				"JOB,"+
				"SALARY,"+
				"DEPT_ID,"+
				"MNG_ID)"+
				"VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			this.preStatement = this.conn.prepareStatement(sql);
			this.preStatement.setInt(1, e.getEmpId());
			this.preStatement.setString(2, e.getEmpName());
			this.preStatement.setString(3, e.getEmpNo());
			this.preStatement.setDate(4, new java.sql.Date(e.getHireDate().getTime()));
			this.preStatement.setBytes(5, e.getImage());
			this.preStatement.setString(6, e.getJob());
			this.preStatement.setFloat(7, e.getSalary());
			this.preStatement.setInt(8, e.getDeptId());
			this.preStatement.setLong(9, e.getMngId().longValue());
			int rowCount=this.preStatement.executeUpdate();
			
			return rowCount;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
			
		}
		
	}

	@Override
	public int update(Employee e) {
		String sql = "UPDATE EMPLOYEE set "+
				"EMP_NAME = ?,"+
				"EMP_NO = ?,"+
				"HIRE_DATE = ?,"+
				"IMAGE= ?,"+
				"JOB= ?,"+
				"SALARY = ?,"+
				"DEPT_ID = ?,"+
				"MNG_ID= ? "+
				"Where EMP_ID = ?";
		try {
			this.preStatement = this.conn.prepareStatement(sql);
			this.preStatement.setString(1, e.getEmpName());
			this.preStatement.setString(2, e.getEmpNo());
			this.preStatement.setDate(3, new java.sql.Date(e.getHireDate().getTime()));
			this.preStatement.setBytes(4, e.getImage());
			this.preStatement.setString(5, e.getJob());
			this.preStatement.setFloat(6, e.getSalary());
			this.preStatement.setInt(7, e.getDeptId());
			this.preStatement.setLong(8, e.getMngId().longValue());
			this.preStatement.setInt(9, e.getEmpId());

			int rowCount=this.preStatement.executeUpdate();
			
			return rowCount;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
			
		}
	}

	@Override
	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// Phương thức để hiển thị tất cả nhân viên trong một phòng ban
	public Employee[] getEmployeesByDepartment(int departmentId) {
		Vector<Employee> employees = new Vector<>();
		String query = "SELECT * FROM EMPLOYEE WHERE DEPT_ID=?";
		Employee[] empArray;
		try {
			preStatement = conn.prepareStatement(query);
			preStatement.setInt(1, departmentId);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setDeptId(rs.getInt("EMP_ID"));
				employee.setEmpName(rs.getString("EMP_NAME"));
				employee.setEmpNo(rs.getString("EMP_NO"));
				// Các thuộc tính khác của Employee
				employees.add(employee);
			}
			empArray = new Employee[employees.size()];
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employees.toArray(empArray);
	}

	// danh sach nhan vien xin nghi của 1 phong ban
	public Employee[] getEmployeesOnLeaveInDepartment(int departmentId) {
		Vector<Employee> employeesOnLeave = new Vector<>();
		String query = "SELECT E.* FROM EMPLOYEE E " +
				"INNER JOIN TIMEKEEPER T ON E.EMP_ID = T.EMP_ID " +
				"WHERE E.DEPT_ID = ? AND T.IN_OUT = 'OUT'";
		Employee[] empArray;

		try {
			preStatement = conn.prepareStatement(query);
			preStatement.setInt(1, departmentId);
			rs = preStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmpId(rs.getInt("EMP_ID"));
				employee.setEmpName(rs.getString("EMP_NAME"));
				employee.setEmpNo(rs.getString("EMP_NO"));
				// Các thuộc tính khác của Employee
				employeesOnLeave.add(employee);
			}
			empArray = new Employee[employeesOnLeave.size()];
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employeesOnLeave.toArray(empArray);
	}
}