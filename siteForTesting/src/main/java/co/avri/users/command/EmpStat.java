package co.avri.users.command;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import co.avri.board.comm.Action;
import co.avri.users.dao.EmpDAO;

public class EmpStat implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// db 조회
		EmpDAO dao = new EmpDAO();
		List<Map<String, Object>> list = dao.getEmpStat();

		// db 조회 결과를 json String으로 전환
		String str = new JSONArray(list).toString();
		
		// response 통해서 출력
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
