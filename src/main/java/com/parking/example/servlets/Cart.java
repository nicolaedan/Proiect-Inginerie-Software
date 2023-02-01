package com.parking.example.servlets;

import com.parking.example.common.ProductCartDto;
import com.parking.example.common.ProductDto;
import com.parking.example.ejb.CartBean;
import com.parking.example.ejb.ProductsBean;
import com.parking.example.ejb.UserBean;
import com.parking.example.entities.User;
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


@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_PRODUCTS"}))
@WebServlet(name = "Cart", value = "/Cart")
public class Cart extends HttpServlet {
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

        List<ProductCartDto> products = productsBean.findAllproductsByUser(productsBean.getUserIdNyName(request.getUserPrincipal().getName()));
        List<ProductDto> productsList = new ArrayList<ProductDto>();
        List<Long> productsid = new ArrayList<Long>();

        Long total_sum = 0L;
        Long price_inc = 0l;
        for (ProductCartDto pcd : products) {
            if (productsBean.findById(pcd.getId_product()) != null) {
                productsList.add(productsBean.findById(pcd.getId_product()));
                productsList.get(productsList.size() - 1).setQuantity(pcd.getQuantity());
                price_inc = productsList.get(productsList.size() - 1).getPrice();
                productsList.get(productsList.size() - 1).setPrice(productsList.get(productsList.size() - 1).getPrice() * Long.parseLong(pcd.getQuantity()));
                total_sum += productsList.get(productsList.size() - 1).getPrice();
                productsList.get(productsList.size() - 1).setId(pcd.getId());
                productsid.add(pcd.getId());
            }
        }
        request.setAttribute("productsList", productsList);
        request.setAttribute("total_sum", total_sum);
        request.setAttribute("productsid", productsid);
        request.setAttribute("price_inc", price_inc);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);

        response.sendRedirect(request.getContextPath() + "/Products");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] productIdsAsStringCart = request.getParameterValues("product_cart_id");
        String btndlt = request.getParameter("submitdlt");
        if (btndlt != null && btndlt.equals("submitdlt")) {
            List<Long> productIds = new ArrayList<>();
            if (productIdsAsStringCart != null) {
                for (String productIdAsString : productIdsAsStringCart) {
                    productIds.add(Long.parseLong(productIdAsString));

                }
                cartBean.deleteProduct(productIds);
            }
        }

        String btnbuy = request.getParameter("submitbuy");
        if (btnbuy != null && btnbuy.equals("submitbuy")) {

            Long sum = Long.valueOf(request.getParameter("total_sum"));
            Long userid = userBean.getUserIdNyName(request.getUserPrincipal().getName());

            User user = entityManager.find(User.class, userid);
            if (user.getMoney_deposited() > sum) {
                cartBean.updateMoneyUser(userid, sum);

                String user_name = request.getUserPrincipal().getName();
                Long id_user = productsBean.getUserIdNyName(user_name);
                List<ProductCartDto> productsList = productsBean.findAllproductsByUser(id_user);
                productsBean.updeteProductQuantity(productsList);
                cartBean.deleteProductsCartByIds(productsList);
            }
        }


        String btnminus = request.getParameter("btnminus");
        if (btnminus != null) {
            Long id_prd = Long.valueOf(btnminus);
            cartBean.updateProducMinus1(id_prd);

        }

        String btnplus = request.getParameter("btnplus");
        if (btnplus != null) {
            Long id_prd = Long.valueOf(btnplus);
            cartBean.updateProducPlus1(id_prd);

        }

        response.sendRedirect(request.getContextPath() + "/Cart");
    }
}

