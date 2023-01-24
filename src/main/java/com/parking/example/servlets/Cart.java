package com.parking.example.servlets;

import com.parking.example.common.ProductCartDto;
import com.parking.example.common.ProductDto;
import com.parking.example.common.UserDto;
import com.parking.example.ejb.InvoiceBean;
import com.parking.example.ejb.ProductsBean;
import com.parking.example.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Cart", value = "/Cart")
public class Cart extends HttpServlet {
    @Inject
    ProductsBean productsBean;
    @Inject
    InvoiceBean invoiceBean;
    @Inject
    UserBean userBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ProductCartDto> products=productsBean.findAllproductsByUser();
        request.setAttribute("products",products);
        if(!invoiceBean.getProductsIds().isEmpty())
        {
            List<ProductCartDto> names=productsBean.findAllproductsByUser();
            request.setAttribute("invoices",products);
        }

        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] userIdsAsString=request.getParameterValues("products_ids");
        if(userIdsAsString!=null){
            List<Long>userIds=new ArrayList<>();
            for (String userIdAsString:userIdsAsString){
                userIds.add(Long.parseLong(userIdAsString));
            }
            invoiceBean.getUserIds().addAll(userIds);
        }
        response.sendRedirect(request.getContextPath()+"/Users");
    }
    }

