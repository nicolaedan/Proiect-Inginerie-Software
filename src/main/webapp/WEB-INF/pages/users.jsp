<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
    <h1 class="padd-10">Users</h1>
    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
        <form method="POST" action="${pageContext.request.contextPath}/Users">
        <a href="${pageContext.request.contextPath}/AddUser" class="button-f back-blue">Add User</a>
        <button class="button-f back-r" type="submit">Invoice</button>
    </c:if>
    <table class="padd-10 back-b back-grey">
        <thead>
        <td></td>
        <td class="txt-c padd-5">Email</td>
        <td class="txt-c padd-5">Username</td>
        </thead>
        <tbody>
        <c:forEach var="users" items="${users}">
            <tr>
                <c:if test="${pageContext.request.isUserInRole('READ_USERS')}">
                    <td class="padd-5">
                        <input type="checkbox" name="user_ids" value="${users.id}"/>
                    </td>
                </c:if>
                <td class="txt-c padd-5">
                        ${users.email}
                </td>
                <td class="txt-c padd-5">
                        ${users.username}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </form>
    <c:if test="${not empty invoices}">
        <h2>Invoices</h2>
        <c:forEach var="username" items="${invoices}" varStatus="status">
            ${status.index + 1}. ${username}
            <br/>
        </c:forEach>
    </c:if>
</t:pageTemplate>