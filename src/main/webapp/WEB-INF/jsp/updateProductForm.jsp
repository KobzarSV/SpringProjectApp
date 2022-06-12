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
            <form:form action="/products/update/${id}" method="post" modelAttribute="product">
                <div class="form-group">

                    <form:label path="name">Product name:</form:label><br>
                    <form:input type="text" class="form-control" id="productName" placeholder="Enter product name" name="productName" path="name"/><form:errors path="name" style="color:red"/><br>

                    <form:label path="price">Price:</form:label><br>
                    <form:input type="number" class="form-control" id="price" placeholder="Enter price" name="price" path="price"/><form:errors path="price" style="color:red"/><br>

                    <form:label path="manufacturer">Select manufacturer:</form:label><br>
                    <form:select path="manufacturer" class="form-control" id="manufacturerId" name="manufacturerId">
                       <c:forEach items="${manufacturer}" var="manufacturer">
                          <form:option path="manufacturer" value="${manufacturer}"><c:out value="${manufacturer.name}"/></form:option>
                       </c:forEach>
                       <form:errors path="manufacturer" style="color:red"/>
                    </form:select><br>

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
