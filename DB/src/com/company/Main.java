package com.company;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library","root","admin");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select book_id from book where book_id<20");
            while(rs.next())
                System.out.println(rs.getInt(1));
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

