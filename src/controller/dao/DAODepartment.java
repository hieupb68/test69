package controller.dao;

import model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAODepartment extends IDAO<Department> {

    public DAODepartment(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department[] selectAll() {
        List<Department> departments = new ArrayList<>();
        Department[] deptArray;
        String query = "SELECT * FROM DEPARTMENT";
        try {
            this.rs = this.statement.executeQuery(query);
            while (rs.next()) {
                Department department = new Department();
                department.setDeptId(rs.getInt("DEPT_ID"));
                department.setDeptName(rs.getString("DEPT_NAME"));
                department.setDeptNo(rs.getString("DEPT_NO"));
                department.setLocation(rs.getString("LOCATION"));
                departments.add(department);
            }
            deptArray = new Department[departments.size()];
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return departments.toArray(deptArray);
    }

    @Override
    public Department[] selectByName(String name) {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM DEPARTMENT WHERE DEPT_NAME LIKE ?";
        Department[] deptArray;
        try {
            this.preStatement = conn.prepareStatement(query);
            preStatement.setString(1, "%" + name + "%");
            rs = preStatement.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDeptId(rs.getInt("DEPT_ID"));
                department.setDeptName(rs.getString("DEPT_NAME"));
                department.setDeptNo(rs.getString("DEPT_NO"));
                department.setLocation(rs.getString("LOCATION"));
                departments.add(department);
            }
            deptArray = new Department[departments.size()];
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return departments.toArray(deptArray);
    }

    @Override
    public int insert(Department dept) {
        String query = "INSERT INTO DEPARTMENT (DEPT_NAME, DEPT_NO, LOCATION) VALUES (?, ?, ?)";
        try {
            preStatement = conn.prepareStatement(query);
            preStatement.setString(1, dept.getDeptName());
            preStatement.setString(2, dept.getDeptNo());
            preStatement.setString(3, dept.getLocation());
            int rowsInserted = preStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Department dept) {
        String query = "UPDATE DEPARTMENT SET DEPT_NAME=?, DEPT_NO=?, LOCATION=? WHERE DEPT_ID=?";
        try {
            preStatement = conn.prepareStatement(query);
            preStatement.setString(1, dept.getDeptName());
            preStatement.setString(2, dept.getDeptNo());
            preStatement.setString(3, dept.getLocation());
            preStatement.setInt(4, dept.getDeptId());
            int rowsUpdated = preStatement.executeUpdate();
            return rowsUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
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
}
