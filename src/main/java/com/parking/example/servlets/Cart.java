package com.parking.example.servlets;

import com.parking.example.common.ProductCartDto;
import com.parking.example.common.ProductDto;
import com.parking.example.ejb.CartBean;
import com.parking.example.ejb.InvoiceBean;
import com.parking.example.ejb.ProductsBean;
import com.parking.example.ejb.UserBean;
import com.parking.example.entities.Product;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    CartBean cartBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<ProductCartDto> products=productsBean.findAllproductsByUser(productsBean.getUserIdNyName(request.getUserPrincipal().getName()));
        List<ProductDto> productsList=new ArrayList<ProductDto>();

        Long total_sum=0L;
        for(ProductCartDto pcd:products)
        {if(productsBean.findById(pcd.getId_product())!=null)
        {
            productsList.add(productsBean.findById(pcd.getId_product()));
            productsList.get(productsList.size()-1).setQuantity(pcd.getQuantity());
            productsList.get(productsList.size()-1).setPrice(productsList.get(productsList.size()-1).getPrice()*Long.parseLong(pcd.getQuantity()));
             total_sum+=productsList.get(productsList.size()-1).getPrice();
        }}
        request.setAttribute("productsList",productsList);
        request.setAttribute("total_sum",total_sum);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);


        response.sendRedirect(request.getContextPath() + "/Products");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         String[] productIdsAsStringCart=request.getParameterValues("product_cart_id");
        if(productIdsAsStringCart!=null){
            List<Long> productIds=new ArrayList<>();
            for(String productIdAsString :productIdsAsStringCart){
                productIds.add(Long.parseLong(productIdAsString));
            }
            cartBean.deleteProductsCartByIds(productIds);

        }
        response.sendRedirect(request.getContextPath() + "/Cart");
    }
    }

