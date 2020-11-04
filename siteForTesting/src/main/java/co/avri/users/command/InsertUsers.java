package co.avri.users.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import co.avri.board.comm.Action;
import co.avri.users.dao.UsersDAO;
import co.avri.users.vo.UsersDTO;

public class InsertUsers implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response)
			{
		//파라미터를 DTO에 담기
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String role = request.getParameter("role");
		
		UsersDTO dto = new UsersDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setGender(gender);
		dto.setRole(role);
		
		//등록 처리
		UsersDAO dao = new UsersDAO();
		dao.insertUser(dto);
		
		//등록된 결과를 조회
		
		
		//등록된 정보를 응답
		try {
			response.getWriter().print(new JSONObject(dto));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}

}
