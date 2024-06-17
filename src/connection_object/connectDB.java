/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection_object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDB {
    Connection connection = null;

    public connectDB() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDiem;integratedSecurity=true;" +
                "encrypt=true;trustServerCertificate=true;";
        connection = DriverManager.getConnection(connectionUrl);
    }

    public connectDB(String databaseName) throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName + ";integratedSecurity=true;" +
                "encrypt=true;trustServerCertificate=true;";

        connection = DriverManager.getConnection(connectionUrl);
    }

    public ResultSet getTable(String tableName) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        statement = connection.createStatement();
        // Thực thi câu lệnh SQL
        String sql = "SELECT * FROM " + tableName;
        resultSet = statement.executeQuery(sql);
        // Xử lý kết quả
        return resultSet;
    }

    public Connection getConnection() {
        return connection;
    }
}
