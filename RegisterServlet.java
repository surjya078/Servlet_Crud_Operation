package CrudOperationUsingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class RegisterServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		PrintWriter pw = res.getWriter();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "hapi2001@");
			PreparedStatement ps = con.prepareStatement("insert into crud_register(email,password) values (?,?)");
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ps.execute();
			
				pw.print("<h1>Registered Successfully....</h1>");
				pw.print("<a href='Index.html'>Click here to go Index Page</a>");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
