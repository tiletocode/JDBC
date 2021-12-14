package ex1;

import java.sql.*;

public class SelectQuery {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://ledx-1.c0puka4oucjv.ap-northeast-2.rds.amazonaws.com/sampledb";
            //jdbc:mysql://aws 엔드포인트 주소/스키마명
            conn = DriverManager.getConnection(url, "fiercegear", "****");
            //DB계정, 비밀번호 기입

            System.out.println("DB서버 연결 성공.");

            stmt = conn.createStatement();
            String sql = "SELECT student_id, student_name, branch FROM students where branch='마포구'";
            //쿼리문
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String student_id = rs.getString(1);
                String student_name = rs.getString(2);
                String branch = rs.getString(3);
                //컬럼index대신 컬럼명을 직접기입해도됨.(대소문자무관)

                System.out.println(student_id + " / " + student_name + " / " + branch);
            }
            rs.close();
            stmt.close();
            conn.close();
            //인스턴스를 닫을땐 선언의 역순으로
        }
        catch(ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
        }
        catch (SQLException e) {
            System.out.println("에러: " + e);
        }
        finally {
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}