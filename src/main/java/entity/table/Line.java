package entity.table;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Line {
    public static int AUTO_ID = 100 ;
    private int id ;
    private float distance;
    private int stopPoint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(int stopPoint) {
        this.stopPoint = stopPoint;
    }
    public Line(){

    }
    public Line(int id, float distance, int stopPoint) {
        this.id = id;
        this.distance = distance;
        this.stopPoint = stopPoint;
    }

    public void inputInfo(){
        this.id = AUTO_ID;
        this.AUTO_ID++;
        inputDistance();
        inputStopPoint();

    }
    public void inputDistance(){
        System.out.println("Nhap khoang cach : ");
        float tempDistance = -1;
        do{
            try{
                tempDistance = new Scanner(System.in).nextFloat();
                if(tempDistance > 0){
                    this.distance = tempDistance;
                    break;
                }
                System.out.print("Khoang cach la so thuc , vui long nhap lai ! ");
            }catch(InputMismatchException ex){
                System.out.print("Khoang cach la so thuc , vui long nhap lai ! ");
            }
        }
        while (true);
    }

    public void inputStopPoint(){
        System.out.println("Nhap so diem dung : ");
        int tempStopPoint = -1;
        do{
            try{
                tempStopPoint = new Scanner(System.in).nextInt();
                if(tempStopPoint > 0){
                    this.stopPoint = tempStopPoint;
                    break;
                }
                System.out.print("So diem dung la so nguyen , vui long nhap lai !");
            }catch(InputMismatchException ex){
                System.out.print("So diem dung la so nguyen ! ");
            }
        }while (true);

    }

    @Override
    public String toString() {
        return "Line [id=" + id + ", distance=" + distance + ", stopPoint=" + stopPoint + "]";
    }
}

