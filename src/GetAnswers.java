

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetAnswers
 */
@WebServlet("/GetAnswers")
public class GetAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAnswers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(df.format(System.currentTimeMillis()));
		System.out.println("in GetAnswers");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(getAnswers());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String getAnswers() {
		String sql = "select answer from questions;";
		
		DBUtil myDbUtil = new DBUtil();
		myDbUtil.connect();
		ResultSet rs = myDbUtil.query(sql);
		StringBuilder answer = new StringBuilder();
		
		try {
			while (rs.next()) {
				answer.append(rs.getString("answer"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			myDbUtil.closeConnect();
		}
		
		return answer.toString();
	}
}
