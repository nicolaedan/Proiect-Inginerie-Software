package com.parking.example.servlets;

import com.parking.example.common.ProductDto;
import com.parking.example.ejb.CartBean;
import com.parking.example.ejb.ProductsBean;
import com.parking.example.ejb.UserBean;
import com.parking.example.entities.ProductCart;
import com.parking.example.entities.User;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sun.awt.windows.WPrinterJob;

import java.io.IOException;
import java.security.Principal;
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

    @Inject
    CartBean cartBean;

    @Inject
    User user;

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

       String productIdstr = request.getParameter("product_id");
        if (productIdstr != null)
        {
            Long productId= Long.valueOf(productIdstr);
            String quantity=request.getParameter("qant"+productIdstr);
            if(!quantity.matches("[0-9]+")) {quantity="1";}

            Long user=productsBean.getUserIdNyName(request.getUserPrincipal().getName());
            cartBean.addCart(productId,quantity,user);

        }

        response.sendRedirect(request.getContextPath()+"/Products");
    }
}
