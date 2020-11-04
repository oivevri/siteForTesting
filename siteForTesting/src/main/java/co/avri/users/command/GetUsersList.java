package co.avri.users.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import co.avri.board.comm.Action;
import co.avri.users.dao.UsersDAO;
import co.avri.users.vo.UsersDTO;

public class GetUsersList implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response)
			{
		//파라미터를 DTO에 담기
		//조회 처리
		UsersDAO dao = new UsersDAO();
		List<UsersDTO> userList =dao.getUserList();
		//조회 정보를 응답
		try {
			response.getWriter().print(new JSONArray(userList));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
