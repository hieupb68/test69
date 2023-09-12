package controller.dao;
import model.Timekeeper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
public class DAOTimekeeper extends IDAO<Timekeeper> {

	public DAOTimekeeper(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Timekeeper[] selectAll() {
        List<Timekeeper> timekeepers = new ArrayList<>();
        Timekeeper[] timekeeperArray;
        String query = "SELECT * FROM TIMEKEEPER";
        try {
            this.rs = this.statement.executeQuery(query);
            while (rs.next()) {
                Timekeeper timekeeper = new Timekeeper();
                timekeeper.setTimekeeper_Id(Integer.toString(rs.getInt("TIMEKEEPER_ID")));
                timekeeper.setDate_Time(rs.getTimestamp("DATE_TIME"));
                timekeeper.setIn_Out(rs.getString("IN_OUT"));
                timekeeper.setEmpId(new BigInteger(Integer.toString(rs.getInt("EMP_ID"))));
                timekeepers.add(timekeeper);
            }
            timekeeperArray = new Timekeeper[timekeepers.size()];
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return timekeepers.toArray(timekeeperArray);
    }

    @Override
    public Timekeeper[] selectByName(String name) {
        List<Timekeeper> timekeepers = new ArrayList<>();
        String query = "SELECT * FROM TIMEKEEPER WHERE EMP_ID LIKE ?";
        Timekeeper[] timekeeperArray;
        try {
            this.preStatement = conn.prepareStatement(query);
            preStatement.setString(1, "%" + name + "%");
            rs = preStatement.executeQuery();
            while (rs.next()) {
                Timekeeper timekeeper = new Timekeeper();
                timekeeper.setTimekeeper_Id(Integer.toString(rs.getInt("TIMEKEEPER_ID")));
                timekeeper.setDate_Time(rs.getTimestamp("DATE_TIME"));
                timekeeper.setIn_Out(rs.getString("IN_OUT"));
                timekeeper.setEmpId(new BigInteger(Integer.toString(rs.getInt("EMP_ID"))));
                timekeepers.add(timekeeper);
            }
            timekeeperArray = new Timekeeper[timekeepers.size()];
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return timekeepers.toArray(timekeeperArray);
    }

    @Override
    public int insert(Timekeeper timekeeper) {
        String query = "INSERT INTO TIMEKEEPER (DATE_TIME, IN_OUT, EMP_ID) VALUES (?, ?, ?)";
        
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, new Timestamp(timekeeper.getDate_Time().getTime()));
            preparedStatement.setString(2, timekeeper.getIn_Out());
            preparedStatement.setBigDecimal(3, new BigDecimal(timekeeper.getEmpId()));

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Return 0 to indicate an error
        }
    }


    @Override
    public int update(Timekeeper timekeeper) {
        String query = "UPDATE TIMEKEEPER SET DATE_TIME=?, IN_OUT=?, EMP_ID=? WHERE TIMEKEEPER_ID=?";
        
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, new Timestamp(timekeeper.getDate_Time().getTime()));
            preparedStatement.setString(2, timekeeper.getIn_Out());
            preparedStatement.setBigDecimal(3, new BigDecimal(timekeeper.getEmpId()));
            preparedStatement.setString(4, timekeeper.getTimekeeper_Id());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Return 0 to indicate an error
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
