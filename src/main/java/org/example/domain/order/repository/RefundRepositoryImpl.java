package org.example.domain.order.repository;

import org.example.domain.order.Refund;
import org.example.domain.order.service.RefundRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.util.DBUtil;

public class RefundRepositoryImpl implements RefundRepository {
    public RefundRepositoryImpl() {
    }

    @Override
    public int save(Long orderId, String refundReason) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DBUtil.getConnection();
            SQL = "Insert Refund(order_id,refund_reason) values(?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, orderId);
            pstmt.setString(2, refundReason);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(pstmt, conn);
        }
    }

    @Override
    public Refund findByOrderId(Long orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;
        ResultSet rs = null;
        Refund refund = null;

        try {
            conn = DBUtil.getConnection();
            SQL = "Select * from Refund where order_id=?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, orderId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                refund = new Refund(rs.getLong("order_id"), rs.getString("refund_reason"));
                refund.setRefundId(rs.getLong("refund_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return refund;
    }

    @Override
    public List<Refund> findAllByUserId(Long userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;
        ResultSet rs = null;
        List<Refund> refunds = new ArrayList<Refund>();

        try {
            conn = DBUtil.getConnection();
            SQL = "select r.refund_id, r.order_id, r.refund_reason from (Refund r join orders o on r.order_id = o.order_id) join users u on u.user_id = o.user_id\n" +
                    "where u.user_id = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Refund refund = new Refund(rs.getLong("order_id"), rs.getString("refund_reason"));
                refund.setRefundId(rs.getLong("refund_id"));
                refunds.add(refund);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return refunds;
    }

    @Override
    public int deleteByOrderId(Long orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String SQL;

        try {
            conn = DBUtil.getConnection();
            SQL = "Delete from Refund where order_id=?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(pstmt, conn);
        }
    }
}
