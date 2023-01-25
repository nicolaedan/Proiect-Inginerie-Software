package com.parking.example.servlets;

import com.parking.example.common.ProductCartDto;
import com.parking.example.common.ProductDto;
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

        List<ProductCartDto> products=productsBean.findAllproductsByUser(request.getUserPrincipal());

        if(!invoiceBean.getProductsIds().isEmpty())
        {
            List<ProductCartDto> names=productsBean.findAllproductsByUser(request.getUserPrincipal());
            request.setAttribute("invoices",products);
        }
        List<ProductDto> productsList=new ArrayList<ProductDto>();

for(ProductCartDto pcd:products)
{
    productsList.add(productsBean.findById(pcd.getId()));
    productsList.get(productsList.size()-1).setQuantity(pcd.getQuantity());
    productsList.get(productsList.size()-1).setPrice(productsList.get(productsList.size()-1).getPrice()*Long.parseLong(pcd.getQuantity()));
}
        request.setAttribute("productsList",productsList);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    }

