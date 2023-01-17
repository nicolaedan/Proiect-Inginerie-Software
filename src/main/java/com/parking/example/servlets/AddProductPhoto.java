package com.parking.example.servlets;

import com.parking.example.common.ProductDto;
import com.parking.example.ejb.ProductsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_PRODUCTS"}))
@MultipartConfig
@WebServlet(name = "AddProductPhoto",value = "/AddProductPhoto")
public class AddProductPhoto extends HttpServlet {
    @Inject
    ProductsBean productsBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long productId=Long.parseLong(request.getParameter("id"));
        ProductDto product= productsBean.findById(productId);
        request.setAttribute("product",product);
        request.getRequestDispatcher("/WEB-INF/pages/addProductPhoto.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long productId=Long.parseLong(request.getParameter("product_id"));

        Part filePart=request.getPart("file");
        String fileName=filePart.getSubmittedFileName();
        String fileType=filePart.getContentType();
        long fileSize=filePart.getSize();
        byte[] fileContent=new byte[(int) fileSize];
        filePart.getInputStream().read(fileContent);

        productsBean.addPhotoToProduct(productId,fileName,fileType,fileContent);
        response.sendRedirect(request.getContextPath()+"/Products");
    }
}