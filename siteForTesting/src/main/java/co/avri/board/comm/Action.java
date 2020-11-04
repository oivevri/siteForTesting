package co.avri.board.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public String exec(HttpServletRequest request, HttpServletResponse response);
}
