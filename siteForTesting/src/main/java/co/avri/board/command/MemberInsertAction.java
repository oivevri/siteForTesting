package co.avri.board.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.avri.board.comm.Action;
import co.avri.board.dao.MemberDAO;
import co.avri.board.vo.MemberVO;

public class MemberInsertAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보를 데이터베이스에 입력한다
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		
		vo.setId(request.getParameter("id")); // 여기 id는 form에서 설정해준 id
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("pw"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate")));
		// 폼에서 넘어오는 값은 전부 String이니까 valueOf 써서 String값을 넣어주면 Date로 나오는걸 쓰자
		
		int n = dao.insert(vo);
		
		// n이 0이면 입력실패. 0이 아니면 insert 성공.
// request.setAttribute("check", n); 는 request 객체에 n을 실어보내주는방법
// 그거말고 페이지로 넘기는거 해보자		
		String page;
		if (n != 0) {
			page = "jsp/member/insertSuccess.jsp";
		} else {
			page = "jsp/member/insertFail.jsp";
		}
		// 입력 성공하든 실패하든 페이지로 넘겨준다
		return page;
	}
}
