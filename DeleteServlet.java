package CrudOperationUsingServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/delete")
public class DeleteServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		
		PrintWriter pw=res.getWriter();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "hapi2001@");
			PreparedStatement ps=con.prepareStatement("delete from user_crud where id=?");
			
			ps.setString(1, id);
			
			int rs=ps.executeUpdate();
			
			if(rs>0) {
				pw.print("<h1>Data Deleted Successfully...</h1>");
				pw.print("<a href='Home.html'>Click here to go Home Page</a>");
			}else {
				pw.print("<h1>No Such Id is Available !! Try Again !!!</h1>");
				pw.print("<a href='Delete.html'>Click here to Try Again !!</a><br><br>");
				pw.print("<a href='Home.html'>Click here to go Home Page</a>");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
