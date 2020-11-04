package co.avri.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "avri";
	private String pw = "avri";
	
	public Connection conn; // db 연결객체
	
	public DAO() {
		// 데이터베이스 다룰때는 항상 try-catch 를 통해 예외처리해준다
		// 예외처리를 왜해주나? 서버는 24시간 돌아가야하는데 db서버가 꺼져있다 -> 이건 프로그램오류가 아니라 db가 꺼져있는거임. 그래서 예외처리해줌!! 예외처리는 오류가 아님! 
		try {
			Class.forName(driver);	
			conn = DriverManager.getConnection(url, user, pw); // 3가지 전달해서 연결
			
			System.out.println("db연결성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(); // 정확히 어떤 에러가 났는지 보려고 e의 결과를 프린트한다
		}
	}
}
