package com.parking.example.ejb;

import com.parking.example.common.ProductCartDto;
import com.parking.example.common.ProductDto;
import com.parking.example.common.ProductPhotoDto;
import com.parking.example.entities.Product;
import com.parking.example.entities.ProductCart;
import com.parking.example.entities.ProductPhoto;
import com.parking.example.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CartBean {



    private static final Logger LOG = Logger.getLogger(CartBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public void addCart(Long id_product,String quantity,Long id_user) {
        LOG.info("Add in Cart");


        ProductCart productCart=new ProductCart();

        productCart.setId_product(id_product);
        productCart.setId_user(id_user);
        productCart.setQuantity(quantity);

        entityManager.persist(productCart);
    }

    public void deleteProductsCartByIds(Collection<Long> productIds) {
        LOG.info("deleteProductCartsByIds");
        for (Long productId : productIds) {
            ProductDto product = entityManager.find(ProductDto.class, productId);
            entityManager.remove(product);
        }
    }
}
