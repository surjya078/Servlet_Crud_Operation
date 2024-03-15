package CrudOperationUsingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		PrintWriter pw = res.getWriter();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "hapi2001@");
			PreparedStatement ps = con.prepareStatement("select * from crud_register where email=? and password=?");

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				pw.print("<h1>Successfully Logged In....Welcome....</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("Home.html");
				rd.include(req, res);
			} else {
				pw.print("<h1>Invalid Credentials !!!</h1>");
				pw.print("<a href='Login.html'>Click Here To Try Again !!</a><br><br>");
				pw.print("<a href='Index.html'>Click Here To Go Indeex Page</a><br><br>");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
