package org.example.repositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url = "jdbc:mysql://localhost:3306/" + System.getenv("DB_SCHEMA");
    public static final String user = System.getenv("DB_USER");
    public static final String password = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("커넥션 생성 오류");
            throw new RuntimeException(e);
        } return conn;
    }

    public static void close(AutoCloseable... closeable) {
        for (AutoCloseable c : closeable) {
            if(c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    System.out.println("close 하다가 에러나는거 아직까지 못보긴 했음");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
