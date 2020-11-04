package Ajax.co.avri.board.command.copy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.avri.board.comm.Action;
import co.avri.board.dao.MemberDAO;
import co.avri.board.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 인증과정을 처리한다
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();

		// 세션은 내가 처음 접근할때 서버가 만들어줌. 그 세션을 이용하겠다
		HttpSession session = request.getSession(false);
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("pw"));
		
		vo = dao.select(vo); // MemberDAO를 실행시킨다
		// select 메소드가 멤버Vo로 돌아서 여기 앞에 vo 인거

		// 세션에 값을 담자
		session.setAttribute("id", vo.getId()); // 세션에 id를 담는것
		session.setAttribute("author", vo.getAuthor()); // 세션에 권한 담는것
		session.setAttribute("name", vo.getName()); // 세션에 이름 담는것
		
		// request.setAttribute : 기존의 리퀘스트 객체에 담는것
		// 앞"" : 변수 명, 뒤 : 내가 실어줄 것. 값
		request.setAttribute("vo", vo); // 해당 멤버가 존재할때 vo값을 실어보냄
		
		return "jsp/main/loginResult.jsp"; // 돌아갈 페이지를 return해준다
	}
}