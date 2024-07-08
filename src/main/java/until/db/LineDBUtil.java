package until.db;

import entity.table.Line;
import until.file.DataUpdateable;
import until.file.DataWritable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineDBUtil implements DataUpdateable<Line> , DataWritable<Line> {
    @Override
    public List<Line> readDataFromDB() {
        List<Line> lineList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlylaixe", "root", "");

            Statement statement = connection.createStatement();

            String sql = "Select id, distance, bus_stop_number from TUYEN ORDER BY id";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                int distance = rs.getInt(2);
                int stopPint = rs.getInt(3);
                Line line = new Line(id, distance, stopPint);
                lineList.add(line);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lineList;
    }

    @Override
    public void writeDataToDB(Line line) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlylaixe", "root", "");

            String sql = "INSERT INTO TUYEN ( id, distance, bus_stop_number) VALUES (?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, line.getId());
            pstmt.setFloat(2, line.getDistance());
            pstmt.setInt(3, line.getStopPoint());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Line data) {

    }
}
