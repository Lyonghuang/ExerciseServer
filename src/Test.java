import java.sql.ResultSet;

public class Test {
	public static void main(String args[]) {
		String sql = "select * from questions;";
		DBUtil myDbUtil = new DBUtil();
		myDbUtil.connect();
		ResultSet rs = myDbUtil.query(sql);
		try {
			while (rs.next()) {
				System.out.print(rs.getInt("id") + " ");
				System.out.print(rs.getString("text") + " ");
				System.out.print(rs.getString("answerA") + " ");
				System.out.print(rs.getString("answerB") + " ");
				System.out.print(rs.getString("answerC") + " ");
				System.out.print(rs.getString("answerD") + " ");
				System.out.print(rs.getString("answer") + "\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		myDbUtil.closeConnect();
	}
}
