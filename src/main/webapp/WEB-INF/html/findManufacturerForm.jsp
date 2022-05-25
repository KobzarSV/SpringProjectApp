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
        <c:import url="${contextPath}/WEB-INF/html/navbar.jsp"/>
        <div class="container">
            <form action="/manufacturer/name/">
                <div class="form-group">
                    <label for="name">Manufacturer name:</label><br>
                    <input type="text" class="form-control" id="manufacturerName" placeholder="Enter manufacturer name" name="name"><br>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>