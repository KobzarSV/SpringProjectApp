<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Spring Shop</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/jsp/navbar.jsp"/>
        <div class="container">

           <table class="table table-hover", style="text-align: center">
              <h4 style="text-align: center">LIST OF ALL USERS</h4>
              <thead>
                  <tr>
                     <td style="text-align: center">Last name</td>
                     <td style="text-align: center">First name</td>
                     <td style="text-align: center">Email</td>
                     <td style="text-align: center">User role</td>
                     <td style="text-align: center">User status</td>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach items="${users}" var="user">
                      <tr>
                          <td>
                             <c:out value="${user.lastName}"/>
                          </td>
                          <td>
                             <c:out value="${user.firstName}"/>
                          </td>
                          <td>
                             <c:out value="${user.email}"/>
                          </td>
                          <td>
                             <c:out value="${user.userRole}"/>
                          </td>
                          <td>
                             <c:out value="${user.userStatus}"/>
                          </td>
                          <td>
                             <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group me-2" role="group" aria-label="Second group">
                                   <a href="/users/form/update/${user.id}" type="button" class="btn btn-primary">Update</a>
                                   <a href="/users/delete/${user.id}" type="button" class="btn btn-danger">Delete</a>
                                </div>
                             </div>
                          </td>
                      </tr>
                  </c:forEach>
              </tbody>
           </table>
        </div>
    </body>
</html>
