package com.ledx.service;

import com.ledx.entity.Notice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeService {
    //다른 쿼리메소드 실행시에도 변하지 않으므로 메소드밖에 선언
    private final String url = "jdbc:mysql://ledx-1.c0puka4oucjv.ap-northeast-2.rds.amazonaws.com/Newlec";
    private final String uid = "fiercegear";
    private final String pwd = "ipaq2210";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    public List<Notice> select() throws ClassNotFoundException, SQLException {

        Class.forName("driver");
        Connection conn = DriverManager.getConnection(url, uid, pwd);
        System.out.println("DB서버 연결 성공.");

        Statement stmt = conn.createStatement();
        String sql = "SELECT * from NOTICE where hit >= 10";
        ResultSet rs = stmt.executeQuery(sql);

        List<Notice> li = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String writer_id = rs.getString(3);
            String content = rs.getString(4);
            Date regdate = rs.getDate(5);
            int hit = rs.getInt(6);
            String files = rs.getString(7);

            Notice ntc = new Notice(id, title, writer_id, content, regdate, hit, files);
            li.add(ntc);

        }
        rs.close();
        stmt.close();
        conn.close();

        return li;
    }
    public int insert(Notice ntc) throws ClassNotFoundException, SQLException {

        int id = ntc.getId();
        String title = ntc.getTitle();
        String writer_id = ntc.getWriter_id();
        String content = ntc.getContent();
        String files = ntc.getFiles();
        Class.forName("driver");
        Connection conn = DriverManager.getConnection(url, uid, pwd);
        System.out.println("DB서버 연결 성공.");
        String sql = "INSERT INTO NOTICE(id, title, writer_id, content, files)" +
                    " values(?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setString(2, title);
        stmt.setString(3, writer_id);
        stmt.setString(4, content);
        stmt.setString(5, files);

        int result = stmt.executeUpdate();
        System.out.println(result + "개의 값이 추가되었습니다.");

        stmt.close();
        conn.close();

        return result;
    }
    public int update(Notice ntc) throws ClassNotFoundException, SQLException {

        String title = ntc.getTitle();
        String content = ntc.getContent();
        String files = ntc.getFiles();
        int id = ntc.getId();

        String sql = "update NOTICE set title = ?, content = ?, files = ? where id = ?";

        Class.forName("driver");
        Connection conn = DriverManager.getConnection(url, uid, pwd);

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, title);
        stmt.setString(2, content);
        stmt.setString(3, files);
        stmt.setInt(4, id);

        int result = stmt.executeUpdate();
        System.out.println(result + "개의 값이 변경되었습니다.");

        stmt.close();
        conn.close();
        return result;
    }

    public int delete(int id) throws ClassNotFoundException, SQLException {

        String sql = "delete from NOTICE where id = ?";

        Class.forName("driver");
        Connection conn = DriverManager.getConnection(url, uid, pwd);

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        int result = stmt.executeUpdate();
        System.out.println(result + "개의 값이 삭제되었습니다.");

        stmt.close();
        conn.close();
        return result;
    }
}

