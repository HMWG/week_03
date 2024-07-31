package org.example.jdbcTest;

import java.sql.*;

public class MainSelect {
    public static void main(String[] args) {
        Connection conn = null;
        String SQL = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // 1. 커넥션 생성
        try {
            //conn = DriverManager.getConnection("jdbc:mysql://59.27.84.200:3306/workshop", "grepp", "grepp");
             conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop", "root", "1234");
            // 2. SQL 작성
            SQL = "select bno,title,writer,publisher,price from book_tb";
            // 3. PreparedStatement or Statement에 명령어 담기
            pstmt = conn.prepareStatement(SQL);
            // 4. 실행하기
            //  4.1 insert/update/delete : int -> .executeUpdate()
            //  4.2 select : ResultSet -> .executeQuery()
            rs = pstmt.executeQuery(); //SQL이 select일 경우에는 .executeQuery()
            // 5. 결과값 처리
            System.out.println("SQL 실행이 완료되었습니다 : ");
            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                /*int no = rs.getInt(1);
                String title = rs.getString(2);
                String writer = rs.getString(3);
                String publisher = rs.getString(4);
                int price = rs.getInt(5);*/

                int no = rs.getInt("bno");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String publisher = rs.getString("publisher");
                int price = rs.getInt("price");
                sb.append(no).append(",").append(title).append(",").append(writer).append(",").append(publisher).append(",").append(price);
                System.out.println(sb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            // 6. 사용 완료한 리소스 반납 ( 생성한 순서의 역순으로 )
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstmt!=null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
