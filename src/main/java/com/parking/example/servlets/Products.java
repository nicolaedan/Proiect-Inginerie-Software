package com.parking.example.servlets;

import com.parking.example.common.ProductDto;
import com.parking.example.ejb.CartBean;
import com.parking.example.ejb.ProductsBean;
import com.parking.example.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@DeclareRoles({"READ_PRODUCTS", "WRITE_PRODUCTS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_PRODUCTS", "WRITE_PRODUCTS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"READ_PRODUCTS", "WRITE_PRODUCTS"})})
@WebServlet(name = "Products", value = "/Products")
public class Products extends HttpServlet {
    @Inject
    ProductsBean productsBean;

    @Inject
    CartBean cartBean;

    @Inject
    UserBean userBean;

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> products= productsBean.findAllproducts();
        List<String>category_group=productsBean.findAllCategories();
        request.setAttribute("categoryGroups",category_group);
        request.setAttribute("products",products);
        request.setAttribute("First","Category");
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productIdsAsString = request.getParameterValues("products_ids");
        if (productIdsAsString != null) {
            List<Long> productIds = new ArrayList<>();
            for (String productIdAsString : productIdsAsString) {
                productIds.add(Long.parseLong(productIdAsString));
                cartBean.deleteProduct(productsBean.findProductInCart(Long.parseLong(productIdAsString)));
            }
            productsBean.deleteProductsByIds(productIds);
        }

        String productIdstr = request.getParameter("product_id");
        if (productIdstr != null) {
            Long productId = Long.valueOf(productIdstr);
            String quantity = request.getParameter("qant" + productIdstr);
            if (!quantity.matches("[0-9]+")) {
                quantity = "1";
            }

            Long user = userBean.getUserIdNyName(request.getUserPrincipal().getName());
            cartBean.addCart(productId, quantity, user);

        }

        response.sendRedirect(request.getContextPath() + "/Products");
    }
}
