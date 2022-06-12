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

        <div class="container">
           <form:form action="/users/" method="post" modelAttribute="userForm">
                <div class="form-group">

                    <form:label path="firstName">First Name:</form:label><br>
                    <form:input type="text" class="form-control" id="userFirstName" placeholder="Enter first name" name="userFirstName" path="firstName"/>
                    <form:errors path="firstName" style="color:red"/><br>

                    <form:label path="lastName">Last name:</form:label><br>
                    <form:input type="text" class="form-control" id="lastName" placeholder="Enter last name" name="lastName" path="lastName"/>
                    <form:errors path="lastName" style="color:red"/><br>

                    <form:label path="email">Email:</form:label><br>
                    <form:input type="text" class="form-control" id="email" placeholder="Enter email" name="email" path="email"/>
                    <form:errors path="email" style="color:red"/>
                    <span style="color:red">${message}</span><br>

                    <form:label path="password">Password name:</form:label><br>
                    <form:input type="password" class="form-control" id="password" placeholder="Enter password" name="password" path="password"/>
                    <form:errors path="password" style="color:red"/><br>

                    <form:label path="userRole">Select role:</form:label><br>
                    <select class="form-control" id="userRole" name="userRole">
                       <c:forEach items="${userRoles}" var="userRole">
                          <option value="${userRole}"><c:out value="${userRole}"/></option><br>
                       </c:forEach>
                       <form:errors path="userRole" style="color:red"/>
                    </select><br>

                    <form:label path="userStatus">Select status:</form:label><br>
                    <select class="form-control" id="userStatus" name="userStatus">
                       <c:forEach items="${userStatuses}" var="userStatus">
                          <option value="${userStatus}"><c:out value="${userStatus}"/></option><br>
                       </c:forEach>
                       <form:errors path="userStatus" style="color:red"/>
                    </select><br>

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
