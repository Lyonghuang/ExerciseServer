

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.*;

/**
 * Servlet implementation class Exercise
 */
@WebServlet("/Exercise")
public class Exercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exercise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(getQues());
	}
	
	private String getQues() {
		String sql = "select text,answerA,answerB,answerC,answerD from questions;";
		JSONArray jsonArray = new JSONArray();
		
		DBUtil myDbUtil = new DBUtil();
		myDbUtil.connect();
		ResultSet rs = myDbUtil.query(sql);
		try {
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("text", rs.getString("text"));
				jsonObject.put("answerA", rs.getString("answerA"));
				jsonObject.put("answerB", rs.getString("answerB"));
				jsonObject.put("answerC", rs.getString("answerC"));
				jsonObject.put("answerD", rs.getString("answerD"));
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			myDbUtil.closeConnect();
		}
		return jsonArray.toString();
	}

}
