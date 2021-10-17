package com.mycompany.app;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!1234" );
        //Class.forName("org.mariadb.jdbc.Driver");


        Connection connection = null;
        // jdbc:mariadb://{host}[:{port}]/[{database}]
        String databaseConn = "jdbc:mariadb://localhost:3307/loadtest";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection =  DriverManager.getConnection(databaseConn, "root", "root");
            System.out.println("Connected to the mariadb");

            //select USERNO, USERID, USERNM, USERPW from users;
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select USERNO, USERID, USERNM, USERPW from users");
            //rs.first();
            while(rs.next()){
                System.out.println(rs.getString("USERNO")+"/"+rs.getString("USERID")+"/"+rs.getString("USERNM")+"/"); //result is "Hello World!"
            }


        } catch (SQLException e) {
            System.out.println("Didn't connected to the mariadb");
            e.printStackTrace();
        } finally {
                try {
                    if(connection != null) connection.close();
                    if(stmt != null) stmt.close();
                    if(rs != null) rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}

