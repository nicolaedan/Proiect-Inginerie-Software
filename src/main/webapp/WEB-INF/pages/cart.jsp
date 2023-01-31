<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cart">
  <h1>Cart</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Cart">
  <c:if test="${pageContext.request.isUserInRole('READ_PRODUCTS')}">
      <button class="btn btn-danger" type="submit" name="submitdlt" value="submitdlt">Delete Products From Cart</button>
  <div class="container text-center">

    <c:forEach var="productsList" items="${productsList}">
      <div class="row">

          <div class="col">
            <input type="checkbox" name="product_cart_id" value="${productsList.id}"/>
          </div>

          <div class="col"> Name Product:  ${productsList.name}</div>
          <div class="col">Price: ${price_inc}</div>
          <div class="col">Qantity:  ${productsList.quantity}</div>
          <div class="col"> Subtotal:   ${productsList.price}</div>

      </div>
         </c:forEach>
  </div>
      <div class="col" name="total_sum"  items="${total_sum}" value="${total_sum}" >Order Total ${total_sum} </div>
      <input type="hidden" name="total_sum" value="${total_sum}"/>
      <input type="hidden" name="productsid" value="${productsid}"/>
      <button class="btn btn-danger" type="submit" name="submitbuy" value="submitbuy"/>Buy</button>
        </c:if>
      </form>





</t:pageTemplate>