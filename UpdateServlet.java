package CrudOperationUsingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		PrintWriter pw=resp.getWriter();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "hapi2001@");
			PreparedStatement ps = con.prepareStatement("update user_crud set email=?,password=? where id=?");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, id);

			int rs = ps.executeUpdate();

			if (rs > 0) {
				pw.print("<h1>Successfully Updated...</h1>");
				pw.print("<a href='Home.html'>Click here to go Home page</a>");
			}else {
				pw.print("<h1>Something Wrong !! Try Again !!</h1>");
				pw.print("<a href='Update.html'>Click here To Try Again</a>");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
