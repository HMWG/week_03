package org.example.jdbcTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class MainUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        String SQL = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // 1. 커넥션 생성
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/workshop", "root", "1234");
            // 2. SQL 작성
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("변경할 책의 bno >> ");
            int bno = Integer.parseInt(br.readLine());
            System.out.print("제목 >> ");
            String title = br.readLine();
            System.out.print("글쓴이 >> ");
            String writer = br.readLine();
            System.out.print("출판사 >> ");
            String publisher = br.readLine();
            System.out.print("가격 >> ");
            int price = Integer.parseInt(br.readLine());
            SQL = "UPDATE book_tb set title = ?, writer = ?, publisher = ? , price = ? Where bno = ?";
            // 3. PreparedStatement or Statement에 명령어 담기
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, title);
            pstmt.setString(2, writer);
            pstmt.setInt(3, price);
            pstmt.setString(4, publisher);
            pstmt.setInt(5, bno);
            // 4. 실행하기
            //  4.1 insert/update/delete : int -> .executeUpdate()
            //  4.2 select : ResultSet -> .executeQuery()
            int result = pstmt.executeUpdate(); //SQL이 select일 경우에는 .executeQuery()
            // 5. 결과값 처리
            System.out.println("SQL 실행이 완료되었습니다 : " + result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
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
