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

    public void deleteProduct(Collection<Long> productIds) {
        LOG.info("deleteProductCartsByIds");
        for (Long productId : productIds) {
            ProductCart product = entityManager.find(ProductCart.class, productId);
            entityManager.remove(product);
        }
    }

    public void deleteProductsCartByIds(Collection<ProductCartDto> productIds) {
        LOG.info("deleteProductCartsByIds");
        for (ProductCartDto productId : productIds) {
            ProductCart product = entityManager.find(ProductCart.class, productId.getId());
            entityManager.remove(product);
        }
    }


    public ProductDto findById(Long productId) {

        Product product = entityManager.find(Product.class, productId);
        ProductDto productscart = new ProductDto(product.getId(), product.getName(),product.getQuantity(), product.getCategory(),product.getPrice());

        return productscart;

    }
    public void updateMoneyUser(Long productId,Long sum) {
        LOG.info("updateProduct");
        User user = entityManager.find(User.class, productId);
        if(user.getMoney_deposited()>sum) {
            user.setMoney_deposited(user.getMoney_deposited() - sum);
        }
        else {
            //print error
        }

    }

    public void updateProducMinus1(Long productId) {
        LOG.info("updateProduct");
        ProductCart product = entityManager.find(ProductCart.class, productId);
        if(Long.valueOf(product.getQuantity())>1) {
            product.setQuantity(Long.toString(Long.valueOf(product.getQuantity()) - 1));
        }

    }
    public void updateProducPlus1(Long productId) {
        LOG.info("updateProduct");
        ProductCart product = entityManager.find(ProductCart.class, productId);
        product.setQuantity(Long.toString(Long.valueOf(product.getQuantity())+1));

    }
}
