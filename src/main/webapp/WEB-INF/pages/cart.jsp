<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cart">
  <h1>Cart</h1>

  <div class="container text-center">
    <c:forEach var="productsList" items="${productsList}">
      <div class="row">
        <c:if test="${pageContext.request.isUserInRole('READ_USERS')}">
          <div class="col">
            <input type="checkbox" name="products_ids" value="${productsList.id}"/>
          </div>
          <div class="col">
              ${productsList.name}
          </div>
          <div class="col">
              ${productsList.category}
          </div>
          <div class="col">
              ${productsList.price}
          </div>
          <div class="col">
              ${productsList.quantity}
          </div>
        </c:if>

      </div>
    </c:forEach>
  </div>

</t:pageTemplate>