package com.model;
import com.entity.Employee;

import java.sql.*;

public class EmployeeModel {
    private Connection connection;
    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection =
                    DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/employees?user=root&password=");
        }
    }

    public boolean register(Employee emp) {
        try {
            initConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into employees (name, address, email, account, password) values (?,?,?,?,?)");
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getAddress());
            preparedStatement.setString(3, emp.getEmail());
            preparedStatement.setString(4, emp.getAccount());
            preparedStatement.setString(5, emp.getPassword());
            if (emp.isValid()){
                preparedStatement.execute();
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Có lỗi xảy ra. Vui lòng thử lại sau. Error: " + ex.getMessage());
        }
        return false;
    }

    public boolean checkExistAccount(String account) {
        try {
            initConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from employees where account = ?");
            preparedStatement.setString(1, account);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String account1 = resultSet.getString("account");
                Employee emp = new Employee(account1);
                if (emp != null){
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Có lỗi xảy ra. Vui lòng thử lại sau. Error: " + ex.getMessage());
        }
        return false;
    }
    public Employee login(String account, String password) {
        try {
            initConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from employees where account = ? and password = ?");
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                String address = resultSet.getString(2);
                String email = resultSet.getString(3);
                String account1 = resultSet.getString(4);
                String password1 = resultSet.getString(5);
                String createdAt = resultSet.getString(6);
                String updatedAt = resultSet.getString(7);
                Employee emp = new Employee(name, address, email, account1, password1, createdAt, updatedAt);
                return emp;
            }

        } catch (Exception ex) {
            System.out.println("Có lỗi xảy ra. Vui lòng thử lại sau. Error: " + ex.getMessage());
        }
        return null;
    }
}
