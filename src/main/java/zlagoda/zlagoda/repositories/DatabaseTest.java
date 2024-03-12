package zlagoda.zlagoda.repositories;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseTest {
    final String URL = "jdbc:mysql://localhost:3306/zlagoda";
    final String USER = "root";
    final String PASSWORD = "MySQL3506";
    final String DRIVER = "com.mysql.cj.jdbc.Driver";
    Connection conn = null;

    public DatabaseTest() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getConnect() {
        Map<String, String> map = new HashMap<>();
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM test");

            while(resultSet.next()) {
                map.put(resultSet.getString("name"), resultSet.getString("lastname"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
