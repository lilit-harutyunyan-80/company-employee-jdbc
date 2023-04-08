package model;


import db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyEmployeeMain {

    private static Scanner scanner = new Scanner(System.in);

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    public static void main(String[] args) {
//        System.out.println("Please input company name, country");
//        String companyStr = scanner.nextLine();
//        String[] companyData = companyStr.split(",");
//        Company company = new Company();
//        company.setName(companyData[0]);
//        company.setCountry(companyData[1]);
//        saveCompanyToDB(company);

        List<Company> companyList = getAllCompaniesFromDB();
        for (Company company : companyList) {
            System.out.println(company);
        }
    }

    private static List<Company> getAllCompaniesFromDB() {
        List<Company> companyList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from company");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                Company company = new Company();
                company.setId(id);
                company.setName(name);
                company.setCountry(country);

                companyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    private static void saveCompanyToDB(Company company) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO company(name,country) " +
                    "VALUES('" + company.getName() + "', '" + company.getCountry() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
