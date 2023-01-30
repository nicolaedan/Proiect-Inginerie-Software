<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cart">
  <h1>Cart</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Cart">
  <c:if test="${pageContext.request.isUserInRole('READ_PRODUCTS')}">
      <button class="btn btn-danger" type="submit">Delete Products From Cart</button>
  <div class="container text-center">

    <c:forEach var="productsList" items="${productsList}">
      <div class="row">

          <div class="col">
            <input type="checkbox" name="product_cart_id" value="${productsList.id}"/>
          </div>

          <div class="col"> Name Product:  ${productsList.name}</div>
          <div class="col">Category: ${productsList.category}</div>
          <div class="col"> Price:   ${productsList.price}
          </div>
          <div class="col">Qantity:  ${productsList.quantity}
          </div>
      </div>
         </c:forEach>
  </div>
      <div class="col" var="total_sum" item="${total_sum}">Total Price ${total_sum} </div>
        </c:if>





</t:pageTemplate>