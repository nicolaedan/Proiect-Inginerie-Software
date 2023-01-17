package com.parking.example.servlets;

import com.parking.example.common.ProductDto;
import com.parking.example.ejb.ProductsBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@DeclareRoles({"READ_PRODUCTS", "WRITE_PRODUCTS"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_PRODUCTS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"WRITE_PRODUCTS"})})
@WebServlet(name = "Products", value = "/Products")
public class Products extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> products= productsBean.findAllproducts();
        request.setAttribute("products",products);
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productIdsAsString=request.getParameterValues("products_ids");
        if(productIdsAsString!=null){
            List<Long> productIds=new ArrayList<>();
            for(String productIdAsString :productIdsAsString){
                productIds.add(Long.parseLong(productIdAsString));
            }
            productsBean.deleteProductsByIds(productIds);
        }
        response.sendRedirect(request.getContextPath()+"/Products");
    }
}
