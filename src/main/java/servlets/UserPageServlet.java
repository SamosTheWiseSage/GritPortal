package servlets;

import Models.UserBean;
import Models.STATE_TYPE;
import Models.USER_TYPE;
import javax.servlet.ServletException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static servlets.LoginServlet.password;
import static servlets.LoginServlet.username;


@WebServlet("/userPage")
public class UserPageServlet extends HttpServlet{
    UserBean beanReturnFromSession;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DisplayUserCourses(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        showForm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DisplayCourses(req, resp);
            req.getAttribute(username);
            req.getAttribute(password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        showForm(req, resp);
    }

private void DisplayCourses (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    //PORT and DbName should be changed
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/GritAcademyPortal", "ReadUser", "ReadUser");
    Statement stmt = con.createStatement(); //System.out.println("hello");
    //TABLENAME should be changed
    PrintWriter out = resp.getWriter();
    resp.setContentType("text/html");
    //String  fname = req.getParameter("username");
  //  String lname = req.getParameter("password");
    UserBean beanReturnFromSession = (UserBean) req.getSession().getAttribute("userBean");
    username = req.getParameter("username");
    password = req.getParameter("password");

        if (beanReturnFromSession.getUserType() == USER_TYPE.students && beanReturnFromSession.getStateType() == STATE_TYPE.Confirmed){
        // System.out.println(beanReturnFromSession.getStateType() + " "+beanReturnFromSession.getUserType());
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
                    "        </nav>" +
                    "<table>";
            out.println(top);
        try {
            ResultSet rs = stmt.executeQuery("select st.fname,courses.namn,courses.YHP,courses.beskrivning,tt.fname,tt.lname from " +
                    "students st inner join Students_Courses on st.id = Students_Courses.StudentID inner Join " +
                    "courses on courses.id = Students_Courses.kursID JOIN TeachersCourses tc ON Students_Courses.kursID=tc.coursesID inner join " +
                    "Teachers tt on tt.id = tc.teachersID where st.username='"+username+"' AND st.password='"+password+"'");
            while (rs.next()){//courses on students.id = courses.id inner join
                //print to console column 1 and 2
                String middle = "<tr style='border: 1px solid black; background-color: #96D4D4;'>" +
                        "<td> Student name:" + rs.getString(1) + "</td><td> Kurs name:" +rs.getString(2) + "</td><td> Kurs YHP:"+ rs.getString(3) +
                        "</td><td> Kurs beskrivning:"+rs.getString(4)+ "</td><td> Teacher Name:"+rs.getString(5)+"</td><td> Teacher Last name:"+rs.getString(6)+"</td></tr>";
                out.println(middle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String bottom = "</table>";
        out.println(bottom);
    } else if (beanReturnFromSession.getUserType() == USER_TYPE.Teachers && beanReturnFromSession.getStateType() == STATE_TYPE.Confirmed) {
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
                    "        </nav>" +
                    "<table>";
            out.println(top);
            try {
                ResultSet rs = stmt.executeQuery("select st.fname,courses.namn,courses.YHP,courses.beskrivning,tt.fname,tt.lname from " +
                        "students st inner join Students_Courses on st.id = Students_Courses.StudentID inner Join " +
                        "courses on courses.id = Students_Courses.kursID JOIN TeachersCourses tc ON Students_Courses.kursID=tc.coursesID inner join" +
                        " Teachers tt on tt.id = tc.teachersID where st.fname='"+username+"' AND st.lname='"+password+"'");
                while (rs.next()){//courses on students.id = courses.id inner join
                    //print to console column 1 and 2
                    String middle = "<tr style='border: 1px solid black; background-color: #96D4D4;'>" +
                            "<td> Student name:" + rs.getString(1) + "</td><td> Kurs name:" +rs.getString(2) + "</td><td> Kurs YHP:"+ rs.getString(3) +
                            "</td><td> Kurs beskrivning:"+rs.getString(4)+ "</td><td> Teacher Name:"+rs.getString(5)+"</td><td> Teacher Last name:"+rs.getString(6)+"</td></tr>";
                    out.println(middle);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String bottom = "</table>";
            out.println(bottom);
    }
    }
    private void DisplayUserCourses (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //PORT and DbName should be changed
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/GritAcademyPortal", "ReadUser", "ReadUser");
        Statement stmt = con.createStatement(); //System.out.println("hello");
        //TABLENAME should be changed
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        UserBean beanReturnFromSession = (UserBean) req.getSession().getAttribute("userBean");
        String  fname = req.getParameter("username");
        String lname = req.getParameter("password");

        System.out.println(beanReturnFromSession);
        if (beanReturnFromSession == null){
            System.out.println("hello there");
            req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);
        } else if (beanReturnFromSession.getUserType() == USER_TYPE.Teachers && beanReturnFromSession.getStateType() == STATE_TYPE.Confirmed) {
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
                   "        </nav>" +
                   "<table>";
           out.println(top); try {
               ResultSet rs2 = stmt.executeQuery("Select * from Students");
               while (rs2.next()){//courses on students.id = courses.id inner join
                   //print to console column 1 and 2
                   String middle = "<tr style='border: 1px solid black; background-color: #96D4D4;'>" +
                           "<td> id:" + rs2.getString(1) + "</td><td> First name:" +rs2.getString(2) + "</td><td> Last name:"+ rs2.getString(3) +
                           "</td><td> Kurs:"+rs2.getString(4)+ "</td></tr>";
                   out.println(middle);
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
            try {
            ResultSet rs = stmt.executeQuery("select * from courses");
            while (rs.next()){//courses on students.id = courses.id inner join
                //print to console column 1 and 2
                String middle = "<tr style='border: 1px solid black; background-color: #96D4D4;'>" +
                        "<td> id:" + rs.getString(1) + "</td><td> First name:" +rs.getString(2) + "</td><td> Last name:"+ rs.getString(3) +
                        "</td><td> Kurs:"+rs.getString(4)+"</td></tr>";
                out.println(middle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}else if (beanReturnFromSession.getUserType() == USER_TYPE.students && beanReturnFromSession.getStateType() == STATE_TYPE.Confirmed){
            // System.out.println(beanReturnFromSession.getStateType() + " "+beanReturnFromSession.getUserType());
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
                    "        </nav>" +
                    "<table>";
            out.println(top);
            try {
                ResultSet rs = stmt.executeQuery("select st.fname,courses.namn,courses.YHP,courses.beskrivning,tt.fname,tt.lname " +
                        "from students st inner join Students_Courses on st.id = Students_Courses.StudentID inner Join courses on " +
                        "courses.id = Students_Courses.kursID JOIN TeachersCourses tc ON Students_Courses.kursID=tc.coursesID inner join " +
                        "Teachers tt on tt.id = tc.teachersID where st.username='"+username+"' AND st.password='"+password+"'");
                while (rs.next()){//courses on students.id = courses.id inner join
                    //print to console column 1 and 2
                    String middle = "<tr style='border: 1px solid black; background-color: #96D4D4;'>" +
                            "<td> Student name:" + rs.getString(1) + "</td><td> Kurs name:" +rs.getString(2) + "</td><td> Kurs YHP:"+ rs.getString(3) +
                            "</td><td> Kurs beskrivning:"+rs.getString(4)+ "</td><td> Teacher Name:"+rs.getString(5)+"</td><td> Teacher Last name:"+rs.getString(6)+"</td></tr>";
                    out.println(middle);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String bottom = "</table>";
        out.println(bottom);

    }
    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println(
                "<br>"
                        + "<div style='border:black solid; width:200px; padding:5px display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px;'>"
                        + "<form style='margin:5px;' action=/userPage method=POST>"
                        + "            <label for=username>First Name:</label>"
                        + "            <input type=text required=true id=username name=username><br><br>"
                        + "             <label for=password>Last Name:</label>"
                        + "            <input type=text required=true id=password name=password><br><br>"
                        + "            <input type=submit value=Submit>"
                        + "        </form>"
                        + "</div>"
                        + "<br>"+ "<table>" +
                        "<button style='display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px; padding:5px;' id=reset onclick=location.href='/userPage'>RESET</button></div>\n"+
                        "</body>"
                        + "</html>"
        );
    }
}
