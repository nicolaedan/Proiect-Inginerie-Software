<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Login">
    <c:if test="${message != null}">
        <div class="alert alert-warning" role="alert">
                ${message}
        </div>
    </c:if>
    <form class="form-signin padd-10" method="post" action="j_security_check">
        <div class="card w-300 h-500 padd-10 al-c">
            <div class="padd-10"><h1 class="h3 mb-3 font-weight-normal">Sign in</h1></div>
            <div class="padd-10"><label for="username" class="sr-only">Username</label></div>
            <div><input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus/></div>
            <div class="padd-10"><label for="password" class="sr-only">Password</label></div>
            <div><input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required/></div>
            <div class="padd-10"><button class="button-f back-blue" type="submit">Sign in</button></div>
        </div>
    </form>
</t:pageTemplate>
