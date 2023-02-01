<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Products">
    <form method="POST" action="${pageContext.request.contextPath}/Products">
        <div class="row padd-10">
            <div class="col padd-10">
                <h1>Products</h1>
            </div>
            <div class="col padd-10">
                <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                    <button class="button-c back-r al-r" type="submit"><span>Delete Products</span></button>
                </c:if>
            </div>
        </div>
        <div class ="flex-container">
            <c:forEach var="product" items="${products}">
                        <div class="card">
                            <div>
                                <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                                    <div><input type="checkbox" name="products_ids" value="${product.id}"/></div>
                                </c:if>
                            </div>
                            <img src="${pageContext.request.contextPath}/ProductPhoto?id=${product.id}" width=100%/>
                            <div><h1>${product.name}</h1></div>
                            <div><p class="f-bold">Category:</p></div>
                            <div><p>${product.category}</p></div>
                            <div><p class="f-bold">Quantity:</p></div>
                            <div><p>${product.quantity}</p></div>
                            <div><p class="f-bold">Price:</p></div>
                            <div><p>${product.price}</p></div>
                            <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                                <div class="col padd-5">
                                    <a class="button-f back-r"
                                       href="${pageContext.request.contextPath}/AddProductPhoto?id=${product.id}" role="button"><i class="fa fa-camera padd-5"></i>Add
                                        Photo</a>
                                </div>
                                <div class="col padd-5">
                                    <a class="button-f back-gr" href="${pageContext.request.contextPath}/EditProduct?id=${product.id}"><i class="fa fa-cogs padd-5"></i>Edit Product</a>
                                </div>
                            </c:if>
                        </div>
            </c:forEach>
        </div>
    </form>
</t:pageTemplate>