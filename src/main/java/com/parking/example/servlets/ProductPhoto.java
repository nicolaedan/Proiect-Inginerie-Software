package com.parking.example.servlets;

import com.parking.example.common.ProductPhotoDto;
import com.parking.example.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="ProductPhoto",value = "/ProductPhoto")
public class ProductPhoto extends HttpServlet {

    @Inject
    ProductsBean productsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId=Integer.parseInt(request.getParameter("id"));
        ProductPhotoDto photo= productsBean.findPhotoByProductId(productId);

        if(photo!=null){
            response.setContentType(photo.getFileType());
            response.setContentLength(photo.getFileContent().length);
            response.getOutputStream().write(photo.getFileContent());

        }else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}