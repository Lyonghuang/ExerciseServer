

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
 * Servlet implementation class CheckAnswers
 */
@WebServlet("/CheckAnswers")
public class CheckAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAnswers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(df.format(System.currentTimeMillis()));
		System.out.println("in CheckAnswers");
		String answer = request.getParameter("answers");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(getResult(answer));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String getResult(String answer) {
		StringBuilder result = new StringBuilder();
		String sql = "select answer from questions;";
		DBUtil myDbUtil = new DBUtil();
		myDbUtil.connect();
		ResultSet rs = myDbUtil.query(sql);
		try {
			int i = 0;
			while (rs.next()) {
				if (rs.getString("answer").charAt(0) == answer.charAt(i)) {
					result.append("T");
				} else {
					result.append("F");
				}
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			myDbUtil.closeConnect();
		}
		return result.toString();
	}
}
