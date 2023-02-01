<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Online Store</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf
("/")) eq '/about.jsp' ? ' active' : ''}" aria-current="page"
                       href="${pageContext.request.contextPath}/about.jsp"><i class="fa fa-info-circle padd-5"></i>About</a>
                </li>
                <li class="nav-item">
                    <c:if test="${pageContext.request.isUserInRole('READ_PRODUCTS')}">
                        <a class="nav-link ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf
("/")) eq '/products.jsp' ? ' active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/Products"><i class="fa fa-reorder padd-5"></i>Products</a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <c:if test="${pageContext.request.isUserInRole('READ_USERS')}">
                        <a class="nav-link ${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf
("/")) eq '/users.jsp' ? ' active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/Users"><i class="fa fa-group padd-5"></i>Users</a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <c:if test="${pageContext.request.isUserInRole('READ_PRODUCTS')}">
                    <a class="nav-link${pageContext.request.requestURI.substring(pageContext.request.requestURI.lastIndexOf
("/")) eq '/cart.jsp' ? ' active' : ''}" aria-current="page" href="${pageContext.request.contextPath}/Cart"><i class="fa fa-cart-plus padd-5"></i>Cart</a>
                </li>
                </c:if>
                <li class="nav-item">
                    <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/AddProduct"><i class="fa fa-plus-square padd-5"></i>Add Product</a>
                    </c:if>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${pageContext.request.getRemoteUser() == null}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Login"><i class="fa fa-sign-in padd-5"></i>Login</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout"><i class="fa fa-sign-out padd-5"></i>Logout</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>