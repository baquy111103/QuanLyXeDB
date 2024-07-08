package service;

import entity.table.Drivers;
import main.Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class DriversService {
    public void showDriver() {
        for(Drivers drivers : Main.driversList) System.out.println(drivers);
    }

    public void addNewDriver(){
        System.out.print("Nhap so lai xe muon them moiw :");
        int driverNumber = -1;
        do{
            try{
                driverNumber = new Scanner(System.in).nextInt();
                if(driverNumber > 0){
                    break;
                }
                System.out.print("So lai xe them moi phai la so nguyen !");
            }catch (InputMismatchException ex){
                System.out.print("So lai xe them moi phai la so nguyen !");
            }
        }while (true);
        for (int i = 0; i < driverNumber; i++) {
            Drivers drivers = new Drivers();
            drivers.inputInfo();
            Main.driversList.add(drivers);
            Main.driverDBUtil.writeDataToDB(drivers);
        }
    }
    public Drivers findDriverById(int driverId){
        for(Drivers drivers : Main.driversList){
            if(drivers.getId() == driverId)
                return drivers;
        }
        return null;
    }

    public void initializeDriverData(){
        List<Drivers> driversList = Main.driverDBUtil.readDataFromDB();
        if(driversList.size()>0){
            Drivers.AUTO_ID = driversList.get(driversList.size()-1).getId() +1;
            Main.driversList = driversList;
            System.out.println(Main.driversList.size());
        }else{
            Main.driversList = new ArrayList<>();
        }
    }
}
