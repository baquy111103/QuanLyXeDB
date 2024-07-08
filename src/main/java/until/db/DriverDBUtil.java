package until.db;

import entity.table.Drivers;
import until.file.DataReadable;
import until.file.DataWritable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDBUtil implements DataReadable<Drivers> , DataWritable<Drivers> {
    @Override
    public List<Drivers> readDataFormDB() {
        return List.of();
    }

    @Override
    public List<Drivers> readDataFromDB() {
        List<Drivers> driverList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlylaixe", "root", "");

            Statement statement = connection.createStatement();

            String sql = "Select id, name, address, phone_number, driver_level from LAIXE ORDER BY id";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String address = rs.getString(3);
                String phoneNumber = rs.getString(4);
                String driveLevel = rs.getString(5);
                Drivers drivers = new Drivers(fullname, address, phoneNumber, id, driveLevel);
                driverList.add(drivers);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return driverList;
    }

    @Override
    public void writeDataToDB(Drivers drivers) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlylaixe", "root", "");

            String sql = "INSERT INTO LAIXE (id, name, address, phone_number, driver_level) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, drivers.getId());
            pstmt.setString(2, drivers.getFullname());
            pstmt.setString(3, drivers.getAddress());
            pstmt.setString(4, drivers.getPhone());
            pstmt.setString(5, drivers.getDriveLevel());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
