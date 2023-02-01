package com.parking.example.servlets;

import com.parking.example.common.ProductDto;
import com.parking.example.ejb.CartBean;
import com.parking.example.ejb.ProductsBean;
import com.parking.example.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "SortByCategory", value = "/SortByCategory")
public class SortByCategory extends HttpServlet {
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    CartBean cartBean;

    @Inject
    ProductsBean productsBean;
    @Inject
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> products= productsBean.findAllProductsByCategory(request.getParameter("Category"));
        request.setAttribute("categoryGroups",productsBean.findAllCategories());
        request.setAttribute("products",products);
        request.setAttribute("First",request.getParameter("Category"));
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
