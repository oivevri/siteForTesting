package co.avri.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.avri.board.vo.MemberVO;

public class MemberDAO extends DAO {
	private PreparedStatement pstmt; // sql명령문 실행. 커넥션을 통해 데이터베이스에 SQL을 날림
	private ResultSet rs; // select 후 결과셋 받기
	// 돌아오는 결과.. 보통 recordSet라고 하지만 자바에서는 resultSet라고 씀
	private MemberVO vo; // dao에 vo 실어서 보냄
	
	// final 상수들은 대부분 대문자 쓴다.. (ctrl+shift+X)
	private final String SELECT_ALL = "SELECT * FROM MEMBER ORDER BY 1";
	private final String SEARCH_ALL = "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM ("
										+ "SELECT * FROM MEMBER ORDER BY ID "
										+ ") A ) B WHERE RN BETWEEN ? AND ?"; // 전체 멤버 + 페이징
	private final String SELECT = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ?"; // 한명 선택
	private final String INSERT = "INSERT INTO MEMBER(ID, NAME, PASSWORD, ADDRESS,TEL,ENTERDATE) VALUES (?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE MEMBER SET NAME = ?, PASSWORD = ?, ADDRESS = ?, TEL = ? WHERE ID = ?";
	private final String DELETE = "DELETE FROM MEMBER WHERE ID = ?";
	
	public List<MemberVO> selectAll() { // 멤버 리스트 전체를 가져오는 메소드
	      List<MemberVO> list = new ArrayList<MemberVO>();

	      try {
	         // 상위 DAO가 conn을 가지고 있어서 별도로 선언 안해줘도 된다.
	         // conn 객체에 내가 처리할 sql을 담아 DBMS에게 보낸다.
	         pstmt = conn.prepareStatement(SELECT_ALL); // DBMS에게 sql 전달
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            vo = new MemberVO();
	            vo.setId(rs.getString("id")); // db 칼럼명이랑 같아야함
	            vo.setName(rs.getString("name"));
	            vo.setPassword(rs.getString("password"));
	            vo.setAddress(rs.getString("address"));
	            vo.setTel(rs.getString("tel"));
	            vo.setAuthor(rs.getString("author"));
	            vo.setEnterdate(rs.getDate("enterdate"));

	            list.add(vo);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }

	      return list;
	   }
	
	
	public List<MemberVO> searchAll(MemberVO mvo){ // 한 행을 찾을때(검색할때) select
		List<MemberVO> list = new ArrayList<MemberVO>();
		// 결과값이 여러개가 들어올수있으니 콜렉션 개체에 담는다(ArrayList)
		try {
			pstmt = conn.prepareStatement(SEARCH_ALL); // dbms에 sql 전달
			pstmt.setInt(1, mvo.getFirst());
			pstmt.setInt(2, mvo.getLast());
			rs = pstmt.executeQuery(); // pstmt 수행결과를 받아서 rs에 담음
			while(rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id")); // rs의 id 결과값을 가져와서 MemberVO의 setId에 담는거
				vo.setName(rs.getString("name"));
				vo.setPassword(rs.getString("password")); // pw는 안담아와도됨
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
				// vo에 다 담았으면 list에 추가해주자
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	public int count(MemberVO vo) { // 전체건수
		int cnt = 0;
		try {
			String SQL = "SELECT COUNT(*) FROM MEMBER";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			rs.next(); // 그래야 첫번째 행으로 감
			cnt = rs.getInt(1); // 천번째 열 count 함수결과를읽고 cnt에 넣어줌
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	
	public MemberVO select(MemberVO vo) { // 한 행을 찾을때(검색할때) select
		try {
			pstmt = conn.prepareStatement(SELECT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			// pstmt에 파라미터 순서대로 id와 패스워드값을 String으로 넣어준다
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}
	public int insert(MemberVO vo) { // Member 테이블에 insert
		int n = 0;
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getTel());
			pstmt.setDate(6, vo.getEnterdate());
			// select query는 무조건 executeQuery, 나머지는 executeUpdate..
			// 그리고 update는 Integer를 리턴한다(n행이 업데이트되었습니다..)
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	public int update(MemberVO vo) { // Member 테이블에 update
		int n = 0;
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getId());
			
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	public int delete(MemberVO vo) { // Member 테이블에서 선택 행 삭제
		int n = 0;
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, vo.getId());
			
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	private void close() { // 커넥션 끊어주는 close()메소드
// 이 메소드는 각각 메소드의 try문이 끝나면, return 되기전에 finally 문을 붙여서 써준다(close메소드를 오버라이드 해서 쓰는것)
		try {
			// 열어준것의 반대방향으로 가자(우리는 connection 연결 -> pstmt -> rs 받았다)
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}