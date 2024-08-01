package org.example.service;

import org.example.domain.User;
import org.example.repository.UserRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sharp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    private Connection conn = null;
    private PreparedStatement ps = null;
    private String SQL = null;
    private ResultSet rs = null;

    @Override
    public void save(User user) {
        SQL = "insert into users(name, email, password, phone_num) values (?, ?, ?, ?)";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));;
            ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhoneNumber());
            int res = ps.executeUpdate();
            System.out.println(res + " 회원 추가 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<User> findAll() {
        SQL = "select * from users";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(addUser());
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<User> findByEmail(String email) {
        SQL = "select * from users where email = ?";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            ps = conn.prepareStatement(SQL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(addUser());
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<User> findByName(String name) {
        SQL = "select * from users where name = ?";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            ps = conn.prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(addUser());
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(User user) {
        SQL = "update set name=?, email=?, phone_num=? where user_id=?";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhoneNumber());
            ps.setLong(4, user.getUserId());
            int res = ps.executeUpdate();
            System.out.println(res + " 회원정보 수정 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteById(Long id) {
        SQL = "delete from users where user_id=?";
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + System.getenv("DB_SCHEMA"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            ps = conn.prepareStatement(SQL);
            ps.setLong(1, id);
            int res = ps.executeUpdate();
            System.out.println(res + " 회원 삭제 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    private User addUser() throws SQLException {
        long id = rs.getLong("user_id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String phoneNum = rs.getString("phone_num");
        LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

        return new User(id, name, email, password, phoneNum, createdAt);
    }

    private void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
