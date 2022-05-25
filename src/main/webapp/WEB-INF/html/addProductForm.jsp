<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>
        <div class="container">
            <form action="/products/" method="post" modelAttribute="ProductDto">
                <div class="form-group">
                    <label path="name">Product name:</label><br>
                    <input type="text" class="form-control" id="productName" placeholder="Enter product name" name="productName" path="name"/><form:errors path="name" style="color:red"/><br>

                    <label path="price">Price:</label><br>
                    <input type="number" class="form-control" id="price" placeholder="Enter price" name="price" path="price"/><form:errors path="price" style="color:red"/><br>

                    <label path="manufacturer">Select manufacturer:</label><br>
                     <c:forEach items="${manufacturer}" var="manufacturer">
                        <checkbox path="manufacturer" value="${manufacturer}"/> <c:out value="${manufacturer.name}"/><br>
                     </c:forEach>
                     <errors path="manufacturer" style="color:red"/><br>
                </div>
                    <input type="submit" value="Submit"/>
           </form>
            <c:if test="${not empty errorMessage}">
              <c:forEach items="${errorMessage.errors}" var="error">
                 <p style="color:red">${error}</p>
              </c:forEach>
            </c:if>
        </div>
    </body>
</html>
