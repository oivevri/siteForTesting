package Ajax.co.avri.board.command.copy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.avri.board.comm.Action;

public class LogoutAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그아웃 과정을 처리한다
		// 갖고있는 세션만 삭제해주면 된다
		HttpSession session = request.getSession();
		
		String name = (String) session.getAttribute("name"); // 세션은 프로퍼티 값이니까 앞에..
		session.invalidate();
		
		request.setAttribute("name", name);
		
		return "jsp/main/logout.jsp";
	}

}
