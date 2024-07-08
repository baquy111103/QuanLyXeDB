package main;

import entity.table.Drivers;
import entity.table.Line;
import entity.table.table.AssignmentTable;
import service.AssignmentService;
import service.DriversService;
import service.LineService;
import until.db.AssignmentDBUtil;
import until.db.DriverDBUtil;
import until.db.LineDBUtil;

import java.util.List;

public class Main {
    public static List<Drivers> driversList;
    public static List<Line> lineList;
    public static List<AssignmentTable> assignmentTableList;
    public static DriverDBUtil driverDBUtil = new DriverDBUtil();
    public static LineDBUtil lineDBUtil = new LineDBUtil();
    public static AssignmentDBUtil assignmentDBUtil = new AssignmentDBUtil();

    public static DriversService driversService = new DriversService();
    public static LineService lineService = new LineService();
    public static AssignmentService assignmentService = new AssignmentService();

    public static void main(String[] args) {
        initializeData();
        menu();
    }

    public static void initializeData() {
        driversService.initializeDriverData();
        lineService.initializeLineData();
        assignmentService.initializeAssignmentTableData();
    }
}
