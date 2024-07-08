package service;

import entity.table.Line;
import main.Main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class LineService {
    public void showLine(){for(Line line : Main.lineList) System.out.println(true);}
    public void addNewLIne(){
        System.out.print("Nhap so tuyen muon them moi : ");
        int lineNumber = -1 ;
        do {
            try{
                lineNumber = new Scanner(System.in).nextInt();
                if(lineNumber>0){
                    break;
                }
                System.out.print("SO tuyen muon them phai la so nguyen , vui loong thu lai ! ");
            }catch (InputMismatchException ex){
                System.out.print("So tuyen muon them phai la so nguyen , vui long thu lai !" );
            }
        }while (true);
        for(int i = 0 ;i <lineNumber;i++){
            Line line = new Line();
            line.inputInfo();
            Main.lineList.add(line);
            Main.lineDBUtil.writeDataToDB(line);
        }
    }
    public Line findLineById(int lineId){
        for(Line line : Main.lineList){
            if(line.getId() == lineId)
                return line;
        }
        return null;
    }
    public void initializeLineData(){
        List<Line> lineList = Main.lineDBUtil.readDataFromDB();
        if(lineList.size() > 0){
            Line.AUTO_ID = lineList.get(lineList.size() - 1).getId() +1;
            Main.lineList = lineList;
        }else{
            Main.lineList = new ArrayList<>();
        }
    }
}
