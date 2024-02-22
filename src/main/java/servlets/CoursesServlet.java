package servlets;

import Models.USER_TYPE;
import Models.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static Models.STATE_TYPE.Confirmed;
import static servlets.LoginServlet.username;

import static Models.UserBean.* ;

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // UserBean beanReturnFromSession = (UserBean) req.getSession().getAttribute(UserBean.USER_TYPE.userType);
       // beanReturnFromSession.getUserType();
      //  System.out.println(beanReturnFromSession);


        try {
            DisplayCourses(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void DisplayCourses (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //PORT and DbName should be changed
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/GritAcademyPortal", "ReadUser", "ReadUser");
        Statement stmt = con.createStatement(); //System.out.println("hello");
        //TABLENAME should be changed
        PrintWriter out = resp.getWriter();
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
                "        </nav>"+
                "<table>";
        out.println(top);

       // ResultSet rs = stmt.executeQuery("select * from students st inner join Students_Courses on st.id = Students_Courses.StudentID inner Join courses on courses.id = Students_Courses.KursID ");
        ResultSet rs = stmt.executeQuery("select * from courses");
        while (rs.next()){
            //print to console column 1 and 2
          String middle = "<tr style='border: 1px solid black; background-color: #96D4D4;'>" +
                    "<td> kurs id:" + rs.getString(1) + "</td><td> kurs name:" +rs.getString(2) + "</td><td> YHP:"+ rs.getString(3) +
                  "</td><td> Kurs Beskrivning:"+rs.getString(4)+"</td></tr>";
            out.println(middle);
        }
        String bottom = "</table>";
        out.println(bottom);
    }


    }

