<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Edit Product">
    <h1>Edit Product</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditProduct">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="" value="${product.name}"
                       required>
                <div class="invalid-feedback">
                    Product is required.
                </div>

            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="quantity">Quantity</label>
                <input type="text" class="form-control" id="quantity" name="quantity" placeholder=""
                       value="${product.quantity}" required>
                <div class="invalid-feedback">
                    Quantity is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="category">Category</label>
                <input type="text" class="form-control" id="category" name="category" placeholder=""
                       value="${product.category}" required>
                <div class="invalid-feedback">
                    Category is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="category">Price</label>
                <input type="text" class="form-control" id="price" name="price" placeholder="" value="${product.price}"
                       required>
                <div class="invalid-feedback">
                    Price is required.
                </div>
            </div>
        </div>

        <input type="hidden" name="product_id" value="${product.id}"/>
        <div>
            <button class=" btn btn-primary btn-lg" type="submit">Save</button>
        </div>

    </form>

</t:pageTemplate>