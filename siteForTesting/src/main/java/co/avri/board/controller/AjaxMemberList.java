package co.avri.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import co.avri.board.comm.Action;
import co.avri.board.dao.MemberDAO;
import co.avri.board.vo.MemberVO;

public class AjaxMemberList implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setFirst(1);
		vo.setLast(5);
		List<MemberVO> list = dao.searchAll(vo);
		JSONArray arr = new JSONArray(list);
		try {
			response.getWriter().print(arr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
