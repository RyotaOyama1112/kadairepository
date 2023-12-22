package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String url = "jdbc:mysql://localhost/yukyudb";
    private static final String user = "root";
    private static final String password = "4649";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public static ResultSet executeQuery(String sql, String... params) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pstmt.setString(i + 1, params[i]);
        }

        return pstmt.executeQuery();
    }

}