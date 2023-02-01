<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cart">
    <h1>Cart</h1>
    <div class="row">
        <div class="col center">Product</div>
        <div class="col center">Price</div>
        <div class="col center">Quantity</div>
        <div class="col center">Subtotal</div>
        <div class="col center">Delete</div>


    </div>
    <div class=" line"></div>
    <div class="p_4"></div>
    <form method="POST" action="${pageContext.request.contextPath}/Cart">

        <c:if test="${pageContext.request.isUserInRole('READ_PRODUCTS')}">

            <c:forEach var="productsList" items="${productsList}">
                <div class="row">

                    <div class="col center">   ${productsList.name}</div>
                    <div class="col center"> ${price_inc}</div>
                    <div class="col center ">
                        <button class="smallbtn" id="minus" name="btnminus" value="${productsList.id}" > - </button>
                            ${productsList.quantity}
                        <button  class="smallbtn" id="plus" name="btnplus" value="${productsList.id}" > + </button>
                    </div>
                    <div class="col center">   ${productsList.price}</div>
                    <div class="col center">
                        <button type="hidden " id="delete" name="delete" value="${productsList.id}">
                            <img type="input" class="round" src="https://cdn-icons-png.flaticon.com/512/1345/1345874.png">
                        </button>

                    </div>
                </div>
            </c:forEach>
            </div>
            <div class="col" >Total Price: ${total_sum}$ </div>
            <div class="col" >Your Deposit: ${user_money}$ </div>
            <input type="hidden" name="total_sum" value="${total_sum}"/>
            <input type="hidden" name="productsid" value="${productsid}"/>
            <button class="btn btn-danger" type="submit" name="submitbuy" value="submitbuy"/>Buy</button>

        </c:if>
    </form>





</t:pageTemplate>