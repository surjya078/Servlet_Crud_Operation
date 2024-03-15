package CrudOperationUsingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetch")
public class FetchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		PrintWriter pw = resp.getWriter();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "hapi2001@");
			PreparedStatement ps = con.prepareStatement("select * from user_crud where id=?");

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				pw.println("Id : "+rs.getInt("id"));
				pw.println("Name : "+rs.getString("name"));
				pw.println("Age : "+rs.getInt("age"));
				pw.println("Gender : "+rs.getString("gender"));
				pw.println("Email : "+rs.getString("email"));
				pw.println("Password : "+rs.getInt("password"));
				pw.println("Mobile No : "+rs.getLong("mobile"));
				pw.println("Address : "+rs.getString("address"));
				
				RequestDispatcher rd=req.getRequestDispatcher("FetchSuccess.html");
				rd.include(req, resp);
			}else {
				pw.print("<h1>No Such Id Available !!</h1>");
				pw.print("<a href='Fetch.html'>Click Here to Try Again</a>");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
