package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdBoardPaginationAction;
import action.AdBoardViewAction;
import action.AdBoardWriteUpdate;
import action.BoardPaginationAction;
import action.CalendarAddAction;
import action.CalendarDeleteAction;
import action.CalendarEditAction;
import action.CalendarFetchAction;
import action.FreeBoardPaginationAction;
import action.FreeBoardViewAction;
import action.FreeBoardWriteUpdate;
import action.SendMailAction;
import action.ThBoardDeleteAction;
import action.ThBoardEditAction;
import action.ThBoardEditViewAction;
import action.ThBoardPaginationAction;
import action.ThBoardViewAction;
import action.ThBoardWriteUpdate;
import action.ThCommentCountAction;
import action.ThConstructUpdate;

public class Controller extends HttpServlet {
  private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
 
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doPost(request, response);
  }
 
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    request.setCharacterEncoding("UTF-8");
    String requestURI = request.getRequestURI();
    String[] URIList = requestURI.split("/");
    String cmdURI = URIList[URIList.length-1];
     
    Action action = null;
     
    if(cmdURI.equals("BoardPaginationAction.do")) {
      action = new BoardPaginationAction();
      action.execute(request, response);    
      RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
      rd.forward(request, response);
    }else if(cmdURI.equals("FreeBoardPaginationAction.do")) {
    	action = new FreeBoardPaginationAction();
        action.execute(request, response);    
        RequestDispatcher rd = request.getRequestDispatcher("FreeBoard.jsp");
        rd.forward(request, response);
    }else if(cmdURI.equals("FreeBoardWriteUpdate.do")) {
    	action = new FreeBoardWriteUpdate();
    	action.execute(request, response);
    }else if(cmdURI.equals("FreeBoardViewAction.do")) {
    	action = new FreeBoardViewAction();
    	action.execute(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("FreeBoard_View.jsp");
    	rd.forward(request, response);
    }else if(cmdURI.equals("AdBoardPaginationAction.do")) {
    	action = new AdBoardPaginationAction();
        action.execute(request, response);    
        RequestDispatcher rd = request.getRequestDispatcher("AdBoard.jsp");
        rd.forward(request, response);
    }else if(cmdURI.equals("AdBoardWriteUpdate.do")) {
    	action = new AdBoardWriteUpdate();
    	action.execute(request, response);
    }else if(cmdURI.equals("AdBoardViewAction.do")) {
    	action = new AdBoardViewAction();
    	action.execute(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("AdBoard_View.jsp");
    	rd.forward(request, response);
    }else if(cmdURI.equals("ThBoardPaginationAction.do")) {
    	action = new ThBoardPaginationAction();
    	action.execute(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("myThread.jsp");
    	rd.forward(request, response);
    }else if(cmdURI.equals("ThBoardWriteUpdate.do")) {
    	action = new ThBoardWriteUpdate();
    	action.execute(request, response);
    }else if(cmdURI.equals("ThBoardViewAction.do")) {
    	action = new ThBoardViewAction();
    	action.execute(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("ThBoard_View.jsp");
    	rd.forward(request, response);
    }else if(cmdURI.equals("ThBoardEditAction.do")) {
    	action = new ThBoardEditAction();
    	action.execute(request, response);
    }else if(cmdURI.equals("ThBoardEditViewAction.do")) {
    	action = new ThBoardEditViewAction();
    	action.execute(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("ThBoard_Edit.jsp");
    	rd.forward(request, response);
    }else if(cmdURI.equals("ThBoardDeleteAction.do")) {
    	action = new ThBoardDeleteAction();
    	action.execute(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("ThBoardPaginationAction.do");
    	rd.forward(request, response);
    }else if(cmdURI.equals("CalendarFetchAction.do")) {
    	action = new CalendarFetchAction();
    	action.execute(request, response);
    }else if(cmdURI.equals("CalendaraddAction.do")) {
    	action = new CalendarAddAction();
    	action.execute(request, response);
    }else if(cmdURI.equals("CalendarDeleteAction.do")) {
    	action = new CalendarDeleteAction();
    	action.execute(request, response);
    }else if(cmdURI.equals("CalendarEditAction.do")) {
    	action = new CalendarEditAction();
    	action.execute(request, response);
    }else if(cmdURI.equals("ThCommentCountAction.do")) {
    	action = new ThCommentCountAction();
    	action.execute(request, response);
    }else if(cmdURI.equals("ThConstructUpdate.do")) {
    	action = new ThConstructUpdate();
    	action.execute(request, response);
    }else if(cmdURI.equals("SendMailAction.do")) {
    	action = new SendMailAction();
    	action.execute(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("forgot-password.jsp");
    	rd.forward(request, response);
    }
  }
}