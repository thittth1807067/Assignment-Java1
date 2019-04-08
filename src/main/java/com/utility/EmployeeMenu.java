package com.utility;

import com.entity.Employee;
import com.model.EmployeeModel;

import java.util.Scanner;

public class EmployeeMenu {
    EmployeeModel model = new EmployeeModel();
    Scanner scanner = new Scanner(System.in);

    public void createMenu() {
        while (true) {
            System.out.println("-----------Menu------------");
            System.out.println("1. Đăng ký.");
            System.out.println("2. Đăng nhập.");
            System.out.println("3. Thoát.");
            System.out.println("----------------------------");
            System.out.println(" Vui lòng nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("----------Đăng ký----------");
                    while (true){
                        System.out.println(" Nhập tên : ");
                        String name = scanner.nextLine();
                        System.out.println(" Nhập địa chỉ: ");
                        String address = scanner.nextLine();
                        System.out.println(" Nhập email: ");
                        String email = scanner.nextLine();
                        System.out.println(" Nhập tài khoản: ");
                        String account = scanner.nextLine();
                        System.out.println(" Nhập mật khẩu: ");
                        String password = scanner.nextLine();
                        Employee emp = new Employee(name,address,email,account,password);
                        if(!model.checkExistAccount(account)){
                            if(model.register(emp)){
                                System.out.println("Đăng kí thành công!");
                                break;
                            } else {
                                System.out.println("Đăng kí thất bại!");
                                emp.printErrors();
                                System.out.println("Vui lòng nhập lại các thông tin: ");
                            }

                        } else {
                            System.err.println("Tài khoản đã tồn tại. Vui lòng nhập tài khoản khác.");
                            System.out.println("Vui lòng nhập lại các thông tin: ");
                        }
                    }
                    break;
                case 2:
                    System.out.println("---------Đăng nhập--------");
                    System.out.println(" Nhập tài khoản: ");
                    String account = scanner.nextLine();
                    System.out.println(" Nhập mật khẩu: ");
                    String password = scanner.nextLine();
                    Employee emp = model.login(account,password);
                    if (emp == null){
                        System.out.println("Sai thông tin tài khoản!");
                    } else {
                        System.out.println("Đăng nhập thành công!");
                        System.out.println("Thông tin nhân viên: ");
                        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","Tên","Địa chỉ","Email","Tài khoản","Mật khẩu","Ngày tạo","Ngày update");
                        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",emp.getName(),emp.getAddress(),emp.getEmail(),emp.getAccount(),emp.getPassword(),emp.getCreatedAt(),emp.getUpdatedAt());
                    }
                    break;
                case 3:
                    System.out.println("Đã thoát chương trình. Hẹn gặp lại.");
                    break;
                default:
                    System.out.println("Lựa chọn sai. Vui lòng lựa chọn lại trong khoảng từ 1 đến 3.");
                    break;
            }
            if (choice == 3) {
                break;
            }
        }
    }
}
