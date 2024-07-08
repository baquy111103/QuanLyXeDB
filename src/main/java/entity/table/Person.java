package entity.table;

import java.util.Scanner;

public class Person {
    protected String fullName;
    protected String address;
    protected String phone;

    public Person() {}
    public Person(String fullname, String address, String phone) {
        this.fullName = fullname;
        this.address = address;
        this.phone = phone;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputInfo(){
        System.out.print("Nhap ten tai xe : ");
        this.fullName = new Scanner(System.in).nextLine();
        System.out.print("Nhap ten dia chi : ");
        this.address = new Scanner(System.in).nextLine();
        intputPhoneNumber();
    }
    public void intputPhoneNumber(){
        System.out.println("Nhap so dien thoai : ");
        String phoneNumber = "";
        do{
            phoneNumber = new Scanner(System.in).nextLine();
            if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")){
                this.phone = phoneNumber;
                break;
            }
                System.out.print("So dien thoai chi chua so , vui long nhap lai !");
        }while (true);
    }
    @Override
    public String toString() {
        return "Person [fullname=" + fullName + ", address=" + address + ", phone=" + phone + "]";
    }
}
