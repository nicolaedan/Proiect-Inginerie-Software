<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Products">
    <h1>Products</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Products">
        <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
        <a class=" btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddProduct">
            Add Product

        </a>
        <button class="btn btn-danger" type="submit">Delete Products </button>
        </c:if>
        <div class ="container text-center">

            <c:forEach var="product" items="${products}">

                <div class="row">
                    <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                    <div class="col">
                        <input type="checkbox" name="product_ids" value="${product.id}"/>

                    </div>
                    </c:if>
                    <div class="col">
                            ${product.name}
                    </div>
                    <div class="col">
                            ${product.quantity}
                    </div>
                    <div class="col">
                            ${product.category}
                    </div>
                    <div class="col">
                            ${product.quantity}
                    </div>
                    <div class="col">
                            ${product.price}
                    </div>
                    <div class="col">
                        <img src="${pageContext.request.contextPath}/ProductPhoto?id=${product.id}" width="48"/>
                    </div>


                    <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                        <div class="col">
                            <a class="btn btn-secondary"
                               href="${pageContext.request.contextPath}/AddProductPhoto?id=${product.id}" role="button">Add
                                Photo</a>
                        </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProduct?id=${product.id}">Edit
                            Product</a>
                    </div>
                    </c:if>

                </div>
            </c:forEach>

        </div>
    </form>
</t:pageTemplate>