package co.avri.users.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.avri.board.dao.DAO;

public class EmpDAO extends DAO {
	PreparedStatement pstmt;
	ResultSet rs;
	
	private static String EMP_STAT = "SELECT DEPARTMENT_ID, COUNT(*) "
									+ "FROM HR.EMPLOYEES "
									+ "WHERE DEPARTMENT_ID IS NOT NULL "
									+ "GROUP BY DEPARTMENT_ID ";
	
	
	public List<Map<String, Object>> getEmpStat(){
		List<Map<String, Object>> empList = new ArrayList<Map<String, Object>>();
        try {
        	pstmt = conn.prepareStatement(EMP_STAT);
            rs = pstmt.executeQuery();
            while(rs.next()){
            	Map<String, Object> map = new HashMap<String, Object>();
            	map.put("department_id", rs.getString("DEPARTMENT_ID"));
            	map.put("cnt", rs.getInt(2));
            	empList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close();
        }
        return empList;
    }    
	
    public void close() {
    	if (conn != null) {
    		try {
    			conn.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }
}
