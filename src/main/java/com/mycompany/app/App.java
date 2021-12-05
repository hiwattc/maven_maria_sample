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
        App apptest = new App();
        //apptest.testMariaDB();
        apptest.testH2DB();
    }
    public void testH2DB(){
        System.out.println( "Hello World! This is H2 Test" );
        Connection connection = null;

        //embedded mode : jdbc:h2:[file:][<path>][databaseName]
        //In-memory mode : jdbc:h2:mem:<databaseName>
        //Server mode : jdbc:h2:tcp://<server>[:<port>]/[<path>]<databaseName>

        String databaseConn = "jdbc:h2:./h2/workspace/chronos"; //embedded mode ok
        //String databaseConn = "jdbc:h2:tcp://localhost:9092/./h2/workspace/chronos"; //server mode ok

        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection =  DriverManager.getConnection(databaseConn, "sa", "");
            System.out.println("Connected to the h2 database");

            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USERINFO");
            //rs.first();
            while(rs.next()){
                System.out.println(rs.getString("USERNAME")+"/"+rs.getString("PASSWORD")+"/"+rs.getString("ENABLED")+"/"); 
            }


        } catch (SQLException e) {
            System.out.println("Didn't connected to the h2db");
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
    public void testMariaDB(){
        System.out.println( "Hello World!1234" );

        Connection connection = null;
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

