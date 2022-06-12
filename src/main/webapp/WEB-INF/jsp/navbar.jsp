 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

    <nav class="navbar navbar-inverse">
       <div class="container-fluid">
           <div class="navbar-header">
              <a class="navbar-brand" href="/">SPRING SHOP</a>
           </div>
              <ul class="nav navbar-nav">
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Manufacturers <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/manufacturer/all">View All Manufacturers</a></li>

                      <security:authorize access="hasRole('ROLE_ADMIN')">
                         <li><a href="/manufacturer/form/add">Create Manufacturer</a></li>
                      </security:authorize>
                    </ul>
                  </li>

                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Products <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/products/all">View All Products</a></li>

                      <security:authorize access="hasRole('ROLE_ADMIN')">
                         <li><a href="/products/form/add">Create Product</a></li>
                      </security:authorize>
                    </ul>
                  </li>

                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a href="/users/all">View All Users</a></li>
                        <li><a href="/users/form/add">Create User</a></li>
                     </ul>
                   </li>

              </ul>
                 <ul class="nav navbar-nav navbar-right">
                   <li>
                      <a style="float: right" href="/logout">Logout</a>
                   </li>
              </ul>
       </div>
    </nav>