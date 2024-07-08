package service;

import dto.AssignmentDto;
import main.Main;
import entity.table.Drivers;
import entity.table.Line;
import entity.table.table.Assignment;
import entity.table.table.AssignmentTable;
import until.file.DataUtil;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AssignmentService {
   private static boolean  isNullOfEmptyDriverOrLine(){
       return Main.driversList.size() == 0 || Main.lineList.size() == 0;

   }
   public void showAssignmentTable(){
       for (AssignmentTable assignmentTable : Main.assignmentTableList)
        System.out.println(assignmentTable);
   }

    public void creatAssignmentTable() {
        if (isNullOfEmptyDriverOrLine()) {
            return;
        }
        Drivers drivers = inputDriverId();

        int lineNumber = inputLineNumber();
        int indexAgssignmentTableExits = findIndexAgssignmentTableExits(drivers.getId());

        if (indexAgssignmentTableExits < 0) {
            // Thêm mới
            List<Assignment> assignmentList = new ArrayList<>();
            createAssignmentTableList(assignmentList, lineNumber, drivers);
            Main.assignmentTableList.add(new AssignmentTable(drivers, assignmentList));
        } else {
            // update bảng phân công đã có trong hệ thống
            updateOrAddAssignmentTableExits(indexAgssignmentTableExits, lineNumber, drivers);
        }
    }
    private void updateOrAddAssignmentTableExits(int indexAgssignmentTableExitsed,int lineNumber, Drivers drivers){
        for (int i = 0; i < lineNumber; i++) {
            Line line = inputLineId(i,drivers);
            int turnNumber = inputTurnNumber(line);
            int indexAssignmentExits = findIndexAssignmentExits(indexAgssignmentTableExitsed,line.getId());
            int turnSumCurrent = 0;
            turnSumCurrent = Main.assignmentTableList.get(indexAgssignmentTableExitsed).getAssignmentList().stream().mapToInt(Assignment::getTurnNumber).sum();

            if(indexAssignmentExits < 0){
                if(turnNumber + turnSumCurrent > 15){
                    System.out.println("So tuyen vuot qua 15");
                }else{
                    Main.assignmentTableList.get(indexAgssignmentTableExitsed).getAssignmentList().add(new Assignment(line,turnNumber));

                    AssignmentDto assignmentDto = new AssignmentDto(drivers,line,turnNumber);
                    Main.assignmentDBUtil.writeDataToDB(assignmentDto);
                }
            }
        }
    }
    private int findIndexAssignmentExits(int indexAgssignmentTableExitsed, int id){
        for (int i = 0; i < Main.assignmentTableList.get(indexAgssignmentTableExitsed).getAssignmentList().size(); i++) {
            if(Main.assignmentTableList.get(indexAgssignmentTableExitsed).getAssignmentList().get(i).getLine().getId() == id)
                return i;
        }
        return -1;
    }

    private void createAssignmentTableList(List<Assignment> assignmentList, int lineNumber, Drivers drivers) {
        for (int i = 0; i < lineNumber; i++) {
            Line line = inputLineId(i,drivers);
            int turnNumber = inputTurnNumber(line);
            int turnSumCurrent = 0;
            turnSumCurrent = assignmentList.stream().mapToInt(Assignment::getTurnNumber).sum();
            if(turnSumCurrent + turnNumber > 15){
                System.out.println("So tuyen vuot qua 15");
            }else{
                assignmentList.add(new Assignment(line,turnNumber));

                AssignmentDto assignmentDto = new AssignmentDto(drivers,line,turnNumber);
                Main.assignmentDBUtil.writeDataToDB(assignmentDto);
            }
        }
    }
    private Drivers inputDriverId(){
       System.out.println("Nhap vao ID cua lai xe ma ban muon them diem :");
       Drivers drivers;
       do{
           try{
               int driverId = new Scanner(System.in).nextInt();
               drivers = Main.driversService.findDriverById(driverId);
               if(!DataUtil.isNullOrEmpty(drivers)){
                   break;
               }
               System.out.print("ID lai xe vua nhap ko ton tai trong he thong !! ");
           }catch (InputMismatchException ex){
               System.out.print("ID phai la so nguyen duong , vui long nhap lai : ");
           }
       }while (true);
       return drivers;
    }
    private int inputLineNumber(){
       System.out.print("Nhap vao so luong tuyen lai xe chay : ");
       int lineNumber = -1;
       do{
           try{
               lineNumber = new Scanner(System.in).nextInt();
               if(lineNumber > 0){
                   break;
               }
               System.out.print("So luong tuyen la so nguyen , vui long nhap lai :");
           }catch (InputMismatchException ex){
               System.out.print("So luong tuyen la so nguyen , vui long nhap lai : ");
           }
       }while (true);
       return lineNumber;
    }

    private int findIndexAgssignmentTableExits(int driverID){
        for (int i = 0; i < Main.assignmentTableList.size(); i++) {
            if(driverID == Main.assignmentTableList.get(i).getDrivers().getId())
                return i;
        }
        return -1;
    }

    private Line inputLineId(int j, Drivers drivers){
       System.out.print("Nhap ID tuyen duong thu " +(j+1)+" ma lai xe "+drivers.getFullname() + " lai : ");
       Line line;
       do{
           try{
               int lineId = new Scanner(System.in).nextInt();
               line = Main.lineService.findLineById(lineId);
               if(!DataUtil.isNullOrEmpty(line)){
                   break;
               }
               System.out.print("ID mon hoc vua nhap khong ton tai trong he thong , vui loong nhap lai : ");
           }catch (InputMismatchException ex){
               System.out.print("ID mon hoc vua nhap khong ton tai trong he thong , vui loong nhap lai : ");
           }
       }while (true);
       return line;
    }
    private int inputTurnNumber(Line line){
       System.out.print("Nhap so luot cua tuyen " + line.getId() + ":");
       int turnNumber = -1;
       do{
           try{
               turnNumber = new Scanner(System.in).nextInt();
               if(turnNumber > 0){
                   break;
               }
               System.out.print("So luot la so nguyen duong , vui long nhap lai : ");
           }catch (InputMismatchException ex){
               System.out.print("So luot la so nguyen duong , khong phai la chu , vui long nhap lai :");
           }
       }while (true);
       return turnNumber;
    }

    public void sortByTurnNumber() {
        Main.assignmentTableList.sort((o1, o2) -> o2.getAssignmentList().size() - o1.getAssignmentList().size());
    }

    public void distanceStatistics() {
        for (AssignmentTable assignmentTable : Main.assignmentTableList) {
            System.out.println(assignmentTable.getDrivers() + " tổng khoảng chạy trong ngày :"
                    + assignmentTable.getAssignmentList().stream().mapToDouble(Assignment::getDistance).sum());
        }
    }
    public void initializeAssignmentTableData() {
        Main.assignmentTableList = Main.assignmentDBUtil.readDataFormDB();
        if (Main.assignmentTableList.isEmpty()) {
            Main.assignmentTableList = new ArrayList<AssignmentTable>();
        }
    }
}
