<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cart">
  <h1>Cart</h1>

  <div class="container text-center">
    <c:forEach var="products" items="${products}">
      <div class="row">
        <c:if test="${pageContext.request.isUserInRole('READ_USERS')}">
          <div class="col">
            <input type="checkbox" name="products_ids" value="${products.id}"/>
          </div>
          <div class="col">
              ${products.id}
          </div>
          <div class="col">
              ${products.id_product}
          </div>
          <div class="col">
              ${products.state}
          </div>
          <div class="col">
              ${products.quantity}
          </div>
        </c:if>

      </div>
    </c:forEach>
  </div>

</t:pageTemplate>