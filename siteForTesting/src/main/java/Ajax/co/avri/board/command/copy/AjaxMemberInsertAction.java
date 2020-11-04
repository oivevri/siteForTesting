package Ajax.co.avri.board.command.copy;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import co.avri.board.comm.Action;
import co.avri.board.dao.MemberDAO;
import co.avri.board.vo.MemberVO;

public class AjaxMemberInsertAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
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
		
		// 아작스는 page 넘기는거 X
		try {
			response.getWriter().print(new JSONObject(vo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
