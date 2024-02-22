package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println();
        req.getRequestDispatcher("JSP/register.jsp").forward(req,resp);
        showForm(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    private void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println(
                "<br>"
                        + "<div style='border:black solid; width:200px; padding:5px display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px;'>"
                        + "<form style='margin:5px;' action=/register method=POST>"
                        + "            <label for=fname>First Name:</label>"
                        + "            <input type=text id=fname name=fname><br><br>"
                        + "             <label for=lname>Last Name:</label>"
                        + "            <input type=text id=lname name=lname><br><br>"
                        +               "<label for=StudentID>StudentID:</label> "
                        +           "<input type=text id=StudentID name=StudentID><br><br>"
                        + "            <input type=submit value=Submit>"
                        + "        </form>"
                        + "</div>"
                        + "<br>"+ "<table>\n" +
                        "<button style='display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px; padding:5px;' id=reset onclick=location.href='/personchooser'>RESET</button></div>\n"+
                        "</body>"
                        + "</html>"
        );
    }
}

