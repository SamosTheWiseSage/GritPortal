package servlets;

import Models.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // System.out.println("this works");
        req.getRequestDispatcher("JSP/index.jsp").forward(req,resp);
      /*  req.getSession().getAttribute("userBean");
        UserBean beanReturnFromSession = (UserBean) req.getSession().getAttribute("userBean");
        beanReturnFromSession.getStateType();
        System.out.println(beanReturnFromSession.getStateType());*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
