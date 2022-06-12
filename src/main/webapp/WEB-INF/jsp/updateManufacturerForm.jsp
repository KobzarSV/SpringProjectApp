<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
        <h4 style="text-align: center">Manufacturer update</h4>
        <div class="container">
            <form:form action="/manufacturer/update/${id}" method="post" modelAttribute="manufacturer">
                <div class="form-group">
                    <form:label path="name">Manufacturer name:</form:label><br>
                    <form:input type="text" class="form-control" id="manufacturerName" placeholder="Enter manufacturer name" name="manufacturerName" path="name"/>
                    <form:errors path="name" style="color:red"/>
                    <span style="color:red">${message}</span><br>
                </div>
                    <input type="submit" value="Submit"/>
            </form:form>
            <c:if test="${not empty errorMessage}">
              <c:forEach items="${errorMessage.errors}" var="error">
                 <p style="color:red">${error}</p>
              </c:forEach>
            </c:if>
        </div>
    </body>
</html>
