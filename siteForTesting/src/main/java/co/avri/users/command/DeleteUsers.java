package co.avri.users.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import co.avri.board.comm.Action;
import co.avri.users.dao.UsersDAO;
import co.avri.users.vo.UsersDTO;

public class DeleteUsers implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response)
			{
		//파라미터를 DTO에 담기
		String id = request.getParameter("id");
		UsersDTO dto = new UsersDTO(id); 
		//삭제 처리
		UsersDAO dao = new UsersDAO();
		dao.deleteUser(dto);
		//삭제된 정보를 응답
		try {
		response.getWriter().print(new JSONObject(dto));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
