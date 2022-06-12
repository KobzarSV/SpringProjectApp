<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
              <h4 style="text-align: center">LIST OF ALL MANUFACTURERS</h4>
              <span style="color:orange">${message}</span><br>

              <thead>
                 <tr>
                    <td style="text-align: center">Manufacturer name</td>
                 </tr>
              </thead>

              <tbody>
                 <c:forEach items="${manufacturers}" var="manufacturer">
                    <tr>
                       <td>
                       <c:out value="${manufacturer.name}"/>
                       </td>

                       <security:authorize access="hasRole('ROLE_ADMIN')">
                       <td>
                          <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                             <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/manufacturer/form/update/${manufacturer.id}" type="button" class="btn btn-primary">Update</a>
                                <a href="/manufacturer/delete?id=${manufacturer.id}" type="button" class="btn btn-danger">Delete</a>
                             </div>
                          </div>
                       </td>
                       </security:authorize>

                    </tr>
                 </c:forEach>
              </tbody>

           </table>
        </div>
    </body>
</html>
