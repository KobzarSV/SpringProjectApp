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
          <h5 style="text-align: center">PRODUCT</h5>
             <thead>
                <tr>
                   <td>Name</td>
                   <td>Price</td>
                </tr>
             </thead>
             <tbody>
                <tr>
                    <td>
                       <c:out value="${product.name}"/>
                    </td>
                    <td>
                        <c:out value="${product.price}"/>
                    </td>
                </tr>
             </tbody>
          </table>
       </div>
    </body>
</html>
