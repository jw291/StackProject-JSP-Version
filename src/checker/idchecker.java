package checker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import dataquery.UserDataDAO;
import jdbc.MySQLConnection;

import javax.servlet.http.HttpServlet;

@WebServlet("/idchecker_1")
public class idchecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public idchecker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		int count = 0;
		String user_id = request.getParameter("memberId");
		Connection conn = null;
		try{
			conn = MySQLConnection.getConnection();//getConnection이 exception
			UserDataDAO dao = UserDataDAO.getInstance();
			System.out.println(user_id);
			count = dao.RowCountFromID(conn, user_id);
			System.out.println(count);
			//response.setContentType("text/html;charset=UTF-8");
			//JSONObject jobj = new JSONObject();
			PrintWriter out = response.getWriter();
		    
			if(count == 0 ){
				//System.out.println("good");
				//jobj.put("res", "good");
				out.print("good");
			}else{
				//System.out.println("bad");
				out.print("bad");
				//response.getWriter().write("bad");
				//jobj.put("res", "bad");
			}
			 
			  //String gson = new Gson().toJson(jobj);
			 //response.setContentType("application/json; charset=UTF-8");	
			  //response.getWriter().print(jobj);
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			//finally block used to close resources
			try {
				if(conn!=null) conn.close();
			} catch(SQLException se){
				se.printStackTrace();
			}//end finally try

		}//end try	
	}
}