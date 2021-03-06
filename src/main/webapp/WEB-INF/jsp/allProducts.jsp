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
              <h4 style="text-align: center">LIST OF ALL PRODUCTS</h4>
              <thead>
                 <tr>
                    <td style="text-align: center">Product name</td>
                    <td style="text-align: center">Price</td>
                    <td style="text-align: center">Manufacturer</td>
                 </tr>
              </thead>
              <tbody>
                 <c:forEach items="${products}" var="product">
                    <tr>
                       <td>
                          <c:out value="${product.name}"/>
                       </td>

                       <td>
                          <c:out value="${product.price}"/>
                       </td>

                       <td>
                          <a href="/manufacturer/id/${product.manufacturer.id}"><c:out value="${product.manufacturer.name}"/></a>
                       </td>

                       <security:authorize access="hasRole('ROLE_ADMIN')">
                       <td>
                          <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                             <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/products/form/update/${product.id}" type="button" class="btn btn-primary">Update</a>
                                <a href="/products/delete/${product.id}" type="button" class="btn btn-danger">Delete</a>
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
