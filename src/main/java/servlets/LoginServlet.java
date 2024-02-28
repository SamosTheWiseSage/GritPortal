package servlets;

import Models.STATE_TYPE;
import Models.USER_TYPE;
import Models.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static Models.STATE_TYPE.Confirmed;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{

 static String username;
static String password;
 static String userType;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println();
        req.getSession().invalidate();
        req.getSession().setAttribute("errorMessage","");
        req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/GritAcademyPortal", "ReadUser", "ReadUser");
            Statement stmt = con.createStatement(); //System.out.println("hello");
            PrintWriter out = resp.getWriter();
        //PORT and DbName should be changed
            req.getSession().setMaxInactiveInterval(50);
        resp.setContentType("text/html");
            UserBean userBean = new UserBean();
         username = req.getParameter("username");
         req.setAttribute("username",username);

         password = req.getParameter("password");
         req.setAttribute("password",password);
         userType = req.getParameter("user_type");
         req.setAttribute("user_type",userType);
            ResultSet rs = null;
         if (userType.equals("students")){
             rs = stmt.executeQuery("select username,password from students where username='"+username+"' AND password='"+password+"'");
         }
        if (userType.equals("students") && rs.next()){
            String top = "<head><title>Hello " + req.getParameter("name") +  "</title></head>"
                    + "<body>"
                    +"<nav style='box-sizing: border-box; text-decoration: none;\n" +
                    "               font-size: 188%;\n" +
                    "               font-weight:lighter;\n" +
                    "               display: inline-block;\n" +
                    "               border: 3px solid black;\n" +
                    "               border-radius: 12px; justify-content: center;\n" +
                    "            display: flex;\n" +
                    "            gap: 30px;'>\n" +
                    "            <a href=\"/\"style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                width: auto;\n" +
                    "                margin-left: 5%;\n" +
                    "                margin-right: auto;\n" +
                    "                border: 50px;\n" +
                    "                border-radius: 50px; \">HOME</a>\n" +
                    "            <a href=\"/courses\" style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                width: auto;\n" +
                    "                margin-left: 5%;\n" +
                    "                margin-right: auto;\n" +
                    "                border: 50px;\n" +
                    "                border-radius: 50px; \">courses</a>\n" +
                    "            <a href=\"/userPage\" style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                width: auto;\n" +
                    "                margin-left: 5%;\n" +
                    "                margin-right: auto;\n" +
                    "                border: 50px;\n" +
                    "                border-radius: 50px; \">Userpage</a>\n" +
                    "                 <a href=\"/login\" style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                                width: auto;\n" +
                    "                                margin-left: 5%;\n" +
                    "                                margin-right: auto;\n" +
                    "                                border: 50px;\n" +
                    "                                border-radius: 50px; \">LOGIN</a>" +
                    "        </nav>";
            out.println(top);
          //courses on students.id = courses.id inner join
                System.out.println(rs.getString(1));
                userBean.setStateType(STATE_TYPE.Confirmed);
                userBean.setUserType(USER_TYPE.students);
                req.getSession().setAttribute("userBean",userBean);

               // req.getRequestDispatcher("JSP/userPage.jsp").forward(req,resp);
           // req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);
             //  resp.getWriter().print((req.getSession().getAttribute("userBean")));
           resp.getWriter().print("LOGGED IN <br>");
            resp.getWriter().print("<td>"+username+"</td><td> "+password+"</td><td> "+userType + "</td>");
        }else if (rs != null &&!rs.next() && userType.equals("students")) {
            req.getSession().setAttribute("errorMessage","Student not found");
            req.getSession().setAttribute("userBean",userBean);
            req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);
            //  System.out.println("jeinfgrijognroj");
        }
            ResultSet rs2 = null;
            if (userType.equals("Teachers") ){
                rs2 = stmt.executeQuery("select username from Teachers where username= '"+username+"' AND password='"+password+"'");
            }

            if (userType.equals("Teachers") && rs2.next()) {
                String top = "<head><title>Hello " + req.getParameter("name") +  "</title></head>"
                    + "<body>"
                    +"<nav style='box-sizing: border-box; text-decoration: none;\n" +
                    "               font-size: 188%;\n" +
                    "               font-weight:lighter;\n" +
                    "               display: inline-block;\n" +
                    "               border: 3px solid black;\n" +
                    "               border-radius: 12px; justify-content: center;\n" +
                    "            display: flex;\n" +
                    "            gap: 30px;'>\n" +
                    "            <a href=\"/\"style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                width: auto;\n" +
                    "                margin-left: 5%;\n" +
                    "                margin-right: auto;\n" +
                    "                border: 50px;\n" +
                    "                border-radius: 50px; \">HOME</a>\n" +
                    "            <a href=\"/courses\" style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                width: auto;\n" +
                    "                margin-left: 5%;\n" +
                    "                margin-right: auto;\n" +
                    "                border: 50px;\n" +
                    "                border-radius: 50px; \">courses</a>\n" +
                    "            <a href=\"/userPage\" style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                width: auto;\n" +
                    "                margin-left: 5%;\n" +
                    "                margin-right: auto;\n" +
                    "                border: 50px;\n" +
                    "                border-radius: 50px; \">Userpage</a>\n" +
                    "                 <a href=\"/login\" style=\"border: 1px solid black; background-color: #96D4D4;  padding: 50px;\n" +
                    "                                width: auto;\n" +
                    "                                margin-left: 5%;\n" +
                    "                                margin-right: auto;\n" +
                    "                                border: 50px;\n" +
                    "                                border-radius: 50px; \">LOGIN</a>" +
                    "        </nav>";
            out.println(top);
         //courses on students.id = courses.id inner join

                userBean.setStateType(STATE_TYPE.Confirmed);
                userBean.setUserType(USER_TYPE.Teachers);
                req.getSession().setAttribute("userBean",userBean);
           System.out.println("workrojeikndkjfbnkhjwrsbgjhsbg");
            resp.getWriter().print("LOGGED IN <br>");
            resp.getWriter().print("<td>"+username+"</td><td> "+password+"</td><td> "+userType + "</td>");
                resp.getWriter().print("<br> TO LOG OUT PLEASE PRESS LOGIN ONCE MORE");
            //req.getRequestDispatcher("JSP/index.jsp").forward(req,resp);
        } else if (rs2 != null &&!rs2.next() && userType.equals("Teachers")) {
            req.getSession().setAttribute("errorMessage","Student not found");
                req.getSession().setAttribute("userBean",userBean);
            req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);
          //  System.out.println("jeinfgrijognroj");
        }
           // req.setAttribute("userBean",userBean);

        } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }
}
