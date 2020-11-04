package Ajax.co.avri.board.command.copy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.avri.board.comm.Action;
import co.avri.board.comm.Paging;
import co.avri.board.dao.MemberDAO;
import co.avri.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원리스트보기 구현
		MemberDAO dao = new MemberDAO(); // 원래 권장사항은 memberDao인데, dao랑 vo는 다들 아니까..
//		MemberVO vo = new MemberVO(); 던져주는 값이 없으니까 vo는 필요없음. 받기만 하니까
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		// 페이지 번호 파라미터를 조회해서 꺼내고
		String strp = request.getParameter("p");
		
		// 파라미터(페이지번호) 없으면 1로 초기화
		int p = 1;
		if (strp != null && ! strp.equals("")) {
			p = Integer.parseInt(strp);
		}
		
		// 전체 레코드 건수 조회
		Paging paging = new Paging();
		paging.setPageUnit(2); // 한페이지에 출력할 건수.레코드수. 디폴트는 10
		paging.setPageSize(3); // 한페이지에 출력할 페이지번호수. 디폴트는 10
		paging.setPage(p); // 현재 페이지를 설정
		MemberDAO cntdao = new MemberDAO(); // 전체수를 내기위해 dao를 한번더..
		MemberVO vo = new MemberVO(); // vo 도 설정해줘야한다
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		paging.setTotalRecord(cntdao.count(vo));
		
		// 페이징 객체를 저장
		request.setAttribute("paging", paging);
		
		// 해당페이지의 목록(리스트)만 조회
		list = dao.searchAll(vo);
		
		// 결과가 왔으면 request 객체에 담아줘야함
		request.setAttribute("members", list); // 넘어온 리스트를 members 라는 이름으로 담아서 쓸래
		// members 변수는 jsp에서 쓰는것임ㅇㅇ
		
		return "jsp/member/memberList.jsp";
	}

}
