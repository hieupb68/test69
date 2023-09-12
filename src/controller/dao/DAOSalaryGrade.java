package controller.dao;
import model.SalaryGrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DAOSalaryGrade extends IDAO<SalaryGrade> {

	public DAOSalaryGrade(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SalaryGrade[] selectAll() {
        List<SalaryGrade> salaryGrades = new ArrayList<>();
        SalaryGrade[] salaryGradeArray;
        String query = "SELECT * FROM SALARY_GRADE";
        try {
            this.rs = this.statement.executeQuery(query);
            while (rs.next()) {
                SalaryGrade salaryGrade = new SalaryGrade();
                salaryGrade.setGrade(rs.getInt("GRADE"));
                salaryGrade.setHighSalary(rs.getInt("HIGH_SALARY"));
                salaryGrade.setLowSalary(rs.getInt("LOW_SALARY"));
                salaryGrades.add(salaryGrade);
            }
            salaryGradeArray = new SalaryGrade[salaryGrades.size()];
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return salaryGrades.toArray(salaryGradeArray);
    }

    @Override
    public SalaryGrade[] selectByName(String name) {
        List<SalaryGrade> salaryGrades = new ArrayList<>();
        String query = "SELECT * FROM SALARY_GRADE WHERE GRADE LIKE ?";
        SalaryGrade[] salaryGradeArray;
        try {
            this.preStatement = conn.prepareStatement(query);
            preStatement.setString(1, "%" + name + "%");
            rs = preStatement.executeQuery();
            while (rs.next()) {
                SalaryGrade salaryGrade = new SalaryGrade();
                salaryGrade.setGrade(rs.getInt("GRADE"));
                salaryGrade.setHighSalary(rs.getInt("HIGH_SALARY"));
                salaryGrade.setLowSalary(rs.getInt("LOW_SALARY"));
                salaryGrades.add(salaryGrade);
            }
            salaryGradeArray = new SalaryGrade[salaryGrades.size()];
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return salaryGrades.toArray(salaryGradeArray);
    }

    @Override
    public int insert(SalaryGrade salaryGrade) {
        String query = "INSERT INTO SALARY_GRADE (GRADE, HIGH_SALARY, LOW_SALARY) VALUES (?, ?, ?)";
        try {
            preStatement = conn.prepareStatement(query);
            preStatement.setInt(1, salaryGrade.getGrade());
            preStatement.setFloat(2, salaryGrade.getHighSalary());
            preStatement.setFloat(3, salaryGrade.getLowSalary());
            int rowsInserted = preStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(SalaryGrade salaryGrade) {
        String query = "UPDATE SALARY_GRADE SET GRADE=?, HIGH_SALARY=?, LOW_SALARY=? WHERE GRADE=?";
        try {
            preStatement = conn.prepareStatement(query);
            preStatement.setInt(1, salaryGrade.getGrade());
            preStatement.setFloat(2, salaryGrade.getHighSalary());
            preStatement.setFloat(3, salaryGrade.getLowSalary());
            preStatement.setInt(4, salaryGrade.getGrade());
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