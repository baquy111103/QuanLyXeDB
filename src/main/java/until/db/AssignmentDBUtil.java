package until.db;

import dto.AssignmentDto;
import entity.table.Drivers;
import entity.table.table.Assignment;
import entity.table.table.AssignmentTable;
import main.Main;
import until.file.DataReadable;
import until.file.DataUpdateable;
import until.file.DataWritable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDBUtil implements DataWritable<AssignmentDto> , DataUpdateable<AssignmentDto> , DataReadable<AssignmentDto> {
    @Override
    public List<AssignmentTable> readDataFromDB() {
        List<AssignmentTable> assignmentTableList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlylaixe", "root", "");

            Statement statement = connection.createStatement();

            String sql = "Select driver_id, bus_line_id, round_trip_number from PHANCONG";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int driverId = rs.getInt(1);
                int lineId = rs.getInt(2);
                int bout = rs.getInt(3);

                //convert
                boolean checkExits = false;
                Assignment assignment = new Assignment(Main.lineService.findLineById(lineId), bout);
                for (AssignmentTable assignmentTable : assignmentTableList) {
                    if (assignmentTable.getDrivers().getId() == driverId) {
                        assignmentTable.getAssignmentList().add(assignment);
                        checkExits = true;
                        break;
                    }
                }
                if (!checkExits) {
                    List<Assignment> assignmentList = new ArrayList<>();
                    assignmentList.add(assignment);
                    assignmentTableList.add(new AssignmentTable(Main.driversService.findDriverById(driverId), assignmentList));
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return assignmentTableList;
    }

    @Override
    public void writeDataToDB(AssignmentDto assignmentDto) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlylaixe", "root", "");

            String sql = "INSERT INTO PHANCONG (driver_id, bus_line_id, round_trip_number) VALUES (?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, assignmentDto.getDrivers().getId());
            pstmt.setInt(2, assignmentDto.getLine().getId());
            pstmt.setInt(3, assignmentDto.getTurnNumber());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AssignmentDto assignmentDto) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlylaixe", "root", "");

            String sql = "UPDATE PHANCONG SET round_trip_number = ? WHERE driver_id=? and bus_line_id =?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, assignmentDto.getTurnNumber());
            pstmt.setInt(2, assignmentDto.getDrivers().getId());
            pstmt.setInt(3, assignmentDto.getLine().getId());
            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
