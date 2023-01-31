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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless

public class ProductsBean {
    private static final Logger LOG = Logger.getLogger(ProductsBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<ProductDto> findAllproducts() {
        LOG.info("findAllProducts");
        try {
            TypedQuery<Product> typedQuery =
                    entityManager.createQuery("SELECT p FROM Product p", Product.class);
            List<Product> products = typedQuery.getResultList();
            return copyProductsToDto(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    public List<Long> findProductInCart(Long id_product){
        try {
            TypedQuery<Long> typedQuery =
                    entityManager.createQuery("SELECT c.id from ProductCart c where c.id_product=:id_product ", Long.class);
            typedQuery.setParameter("id_product",id_product);
            List<Long> productListInCart=typedQuery.getResultList();
            return productListInCart;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public Long getUserIdNyName(String name){
        LOG.info("getUserIdNyName");
        try {
            TypedQuery<Long> typedQuery =
                    entityManager.createQuery("SELECT u.id from User u where u.username=:name ", Long.class);
            typedQuery.setParameter("name",name);
            Long id_user=typedQuery.getSingleResult();
            return id_user;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    public List<ProductCartDto> findAllproductsByUser(Long  userPrincipal) {
        LOG.info("findAllProductsByUser");
        try {
            TypedQuery<ProductCart> typedQuery =
                    entityManager.createQuery("SELECT p FROM ProductCart p WHERE p.id_user = :userPrincipal", ProductCart.class);
            typedQuery.setParameter("userPrincipal",userPrincipal);
            List<ProductCart> productsCart = typedQuery.getResultList();
            return copyProductsCartToDto(productsCart);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    private List<ProductCartDto> copyProductsCartToDto(List<ProductCart> productsCart) {
        List<ProductCartDto> productCartDto;
        productCartDto = productsCart
                .stream()
                .map(x -> new ProductCartDto(x.getId_product(), x.getId_user(), x.getState(), x.getId(),x.getQuantity())).collect(Collectors.toList());
        return productCartDto;
    }
    private List<ProductDto> copyProductsToDto(List<Product> products) {
        List<ProductDto> productDto;
        productDto = products
                .stream()
                .map(x -> new ProductDto(x.getId(), x.getName(), x.getQuantity(), x.getCategory(),x.getPrice())).collect(Collectors.toList());
        return productDto;
    }

    public void createProduct(String name, String quantity, String category,Long price) {
        LOG.info("createProduct");
        Product product=new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setPrice(price);
        entityManager.persist(product);
    }

    public ProductDto findById(Long productId) {

        Product product = entityManager.find(Product.class, productId);
        ProductDto products = new ProductDto(product.getId(), product.getName(),product.getQuantity(), product.getCategory(),product.getPrice());

        return products;

    }

    public void updateProduct(Long productId, String name, String quantity, String category,Long price) {
        LOG.info("updateProduct");
        Product product = entityManager.find(Product.class, productId);
        product.setName(name);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setPrice(price);
    }

    public void deleteProductsByIds(Collection<Long> productIds) {
        LOG.info("deleteProductsByIds");
        for (Long productId : productIds) {
            Product product = entityManager.find(Product.class, productId);
            entityManager.remove(product);
        }
    }
    public void addPhotoToProduct(Long productId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToProduct");
        ProductPhoto photo = new ProductPhoto();
        photo.setFilename(filename);
        photo.setFiletype(fileType);
        photo.setFileContent(fileContent);
        Product product = entityManager.find(Product.class, productId);
        if (product.getPhoto() != null) {
            entityManager.remove(product.getPhoto());
        }
        product.setPhoto(photo);
        photo.setProduct(product);
        entityManager.persist(photo);
    }
    public ProductPhotoDto findPhotoByProductId(Integer productId) {
        List<ProductPhoto> photos = entityManager
                .createQuery("SELECT p FROM ProductPhoto p where p.product.id = :id", ProductPhoto.class)
                .setParameter("id", productId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        ProductPhoto photo = photos.get(0); // the first element
        return new ProductPhotoDto(photo.getId(), photo.getFilename(), photo.getFiletype(),
                photo.getFileContent());
    }

}