package ex1;

import java.sql.*;

public class InsertInto {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int id = 7;
        String title = "no sequence";
        String writer_id = "tiletocode";
        String content = "plz get nextval()";
        String files = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //jdbc:mysql://aws 엔드포인트 주소/스키마명
            String url = "jdbc:mysql://ledx-1.c0puka4oucjv.ap-northeast-2.rds.amazonaws.com/Newlec";
            conn = DriverManager.getConnection(url, "fiercegear", "****");
            //DB계정, 비밀번호 기입
            System.out.println("DB서버 연결 성공.");

            //쿼리문
            String sql = "INSERT INTO NOTICE(id, title, writer_id, content)" +
                                    " values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, writer_id);
            stmt.setString(4, content);

            int result = stmt.executeUpdate();
            System.out.println(result + "개의 값이 추가되었습니다.");

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