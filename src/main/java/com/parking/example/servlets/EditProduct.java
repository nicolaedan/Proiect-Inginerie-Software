package com.parking.example.servlets;

import com.parking.example.common.ProductDto;
import com.parking.example.common.UserDto;
import com.parking.example.ejb.ProductsBean;
import com.parking.example.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_PRODUCTS"}))
@WebServlet(name = "EditProduct", value = "/EditProduct")
public class EditProduct extends HttpServlet {
    @Inject
    UserBean usersBean;
    @Inject
    ProductsBean productsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);
        Long productId = Long.parseLong(request.getParameter("id"));
        ProductDto product = productsBean.findById(productId);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/editProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String category = request.getParameter("category");
        Long productId = Long.parseLong(request.getParameter("product_id"));
        Long price = Long.parseLong(request.getParameter("price"));

        productsBean.updateProduct(productId, name, quantity, category, price);
        response.sendRedirect(request.getContextPath() + "/Products");
    }
}
