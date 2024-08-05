package org.example.domain.user.repository;

import org.example.domain.delivery.DeliveryAddress;
import org.example.domain.user.User;
import org.example.domain.user.exception.EmailNameDuplicationException;
import org.example.domain.user.service.UserRepository;
import org.example.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private String SQL = null;
    private ResultSet rs = null;

    @Override
    public void save(User user) {
        SQL = "insert into users(name, email, password, phone_num, is_admin) values (?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhoneNumber());
            ps.setBoolean(5, user.getIsAdmin());
            int res = ps.executeUpdate();
            System.out.println(res + " 회원 추가 완료");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EmailNameDuplicationException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    @Override
    public List<User> findAll() {
        SQL = "select * from users";
        try {
            conn = DBUtil.getConnection();
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
            DBUtil.close(conn, ps, rs);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = Optional.empty();
        SQL = "select * from users where user_id = ?";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = Optional.of(addUser());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = Optional.empty();
        SQL = "select * from users where email = ?";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = Optional.of(addUser());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        return user;
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> user = Optional.empty();
        SQL = "select * from users where name = ?";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = Optional.of(addUser());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return user;
    }

    @Override
    public void update(User user) {
        SQL = "update users set name=?, password=?, email=?, phone_num=? where user_id=?";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setLong(5, user.getUserId());
            int res = ps.executeUpdate();
            System.out.println(res + " 회원정보 수정 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    @Override
    public void deleteById(Long id) {
        SQL = "delete from users where user_id=?";
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            ps.setLong(1, id);
            int res = ps.executeUpdate();
            System.out.println(res + " 회원 삭제 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    private User addUser() throws SQLException {
        long id = rs.getLong("user_id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String phoneNum = rs.getString("phone_num");
        boolean isAdmin = rs.getBoolean("is_admin");
        LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);

        return new User(id, name, email, password, phoneNum, isAdmin, createdAt);
    }
}
