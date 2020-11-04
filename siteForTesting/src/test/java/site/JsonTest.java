package site;

import java.util.List;

import org.json.JSONArray;
import org.junit.Test;

import co.avri.board.dao.MemberDAO;
import co.avri.board.vo.MemberVO;

public class JsonTest {

	@Test
	public void jsonobject() {
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		List<MemberVO> list = dao.selectAll();
		new JSONArray(list);
		
		vo.setFirst(1);
		vo.setLast(5);
		dao = new MemberDAO(); // 두번쓰려면 dao 한번더만들어야함
		List<MemberVO> test = dao.searchAll(vo);
		JSONArray arr = new JSONArray(test);
		System.out.println(arr);
	}
}
