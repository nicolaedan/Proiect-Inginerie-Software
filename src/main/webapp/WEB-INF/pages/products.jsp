<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Products">
    <form method="GET" action="${pageContext.request.contextPath}/SortByCategory">
        <div class="row padd-10">
            <div class="col padd-10">
                <h1>Products</h1>
            </div>
            <div class="col"></div>
            <div class="col padd-10">
                <select class="custom-select d-block w-100" id="Category" name="Category">
                    <c:if test="${First!='All'  }">
                        <option value="${First}">${First}</option>
                    </c:if>
                    <option value="All">All</option>
                    <c:forEach var="category_group" items="${categoryGroups}" varStatus="status">
                        <c:if test="${First!=category_group  }">
                            <option value="${category_group}">${category_group}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="col padd-10">
                <button class="btn btn-danger" type="submit">Search</button>
            </div>
        </div>
    </form>
    <form method="POST" action="${pageContext.request.contextPath}/Products">
        <div class="row padd-10">
            <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                <button class="button-c back-r al-r" type="submit"><span>Delete Products</span></button>
            </c:if>
        </div>
        <div class="flex-container">
            <c:forEach var="product" items="${products}">
                <div class="card">
                    <div>
                        <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                            <div class="col">
                                <input type="checkbox" name="products_ids" value="${product.id}"/>

                            </div>
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
                    <c:if test="${pageContext.request.isUserInRole('READ_PRODUCTS')}">
                        <div>
                            <input type="number" min="1" name="qant${product.id}" placeholder="Qantity"/>
                        </div>
                        <div>
                            <button class="button-f back-org" type="submit" name="product_id" value="${product.id}">Add in Cart</button>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </form>
</t:pageTemplate>