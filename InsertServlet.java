package CrudOperationUsingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/insert")
public class InsertServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, javax.servlet.ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		
		

		try {

			PrintWriter pw=res.getWriter();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "hapi2001@");
			PreparedStatement ps=con.prepareStatement("insert into user_crud(id,name,age,gender,email,password,mobile,address) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, age);
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setString(6, password);
			ps.setString(7, mobile);
			ps.setString(8, address);
			
			ps.execute();
			pw.print("<h1>Data Inserted Successfully....</h1>");
			pw.print("<a href='Home.html'>Click here to go Home Page</a>");
			
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
