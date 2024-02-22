<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@ include file="fragments/head.jsp" %>
<body>
<%@ include file="fragments/navbar.jsp" %>
  <br>
                        <div style='border:black solid; width:200px; padding:5px display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px;'>
                         <form style='margin:5px;' action=/register method=POST>
                                     <label for=fname>First Name:</label>
                                  <input type=text id=fname name=fname><br><br>
                                     <label for=lname>Last Name:</label>
                                    <input type=text id=lname name=lname><br><br>
                                     <label for=StudentID>StudentID:</label>
                                  <input type=text id=StudentID name=StudentID><br><br>
                                    <input type=submit value=Submit>
                                 </form>
                         </div>
                   <br><table>\n
                        <button style='display:block; margin-left:auto; margin-right:auto; margin-top:5px; margin-bottom:5px; padding:5px;' id=reset onclick=location.href='/personchooser'>RESET</button></div>
                        </body>
                        </html>
<%@ include file="fragments/footer.jsp" %>

</body>
</html>