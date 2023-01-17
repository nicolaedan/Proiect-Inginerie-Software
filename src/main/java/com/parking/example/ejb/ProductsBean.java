package com.parking.example.ejb;

import com.parking.example.common.ProductDto;
import com.parking.example.common.ProductPhotoDto;
import com.parking.example.entities.Product;
import com.parking.example.entities.ProductPhoto;
import com.parking.example.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

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

    private List<ProductDto> copyProductsToDto(List<Product> products) {
        List<ProductDto> productDto;
        productDto = products
                .stream()
                .map(x -> new ProductDto(x.getId(), x.getName(), x.getQuantity(), x.getCategory())).collect(Collectors.toList());
        return productDto;
    }

    public void createProduct(String name, String quantity, String category) {
        LOG.info("createProduct");
        Product product=new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setCategory(category);
        entityManager.persist(product);
    }

    public ProductDto findById(Long productId) {

        Product product = entityManager.find(Product.class, productId);
        ProductDto products = new ProductDto(product.getId(), product.getName(),product.getQuantity(), product.getCategory());

        return products;

    }

    public void updateProduct(Long productId, String name, String quantity, String category) {
        LOG.info("updateProduct");
        Product product = entityManager.find(Product.class, productId);
        product.setName(name);
        product.setQuantity(quantity);
        product.setCategory(category);
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
                .createQuery("SELECT p FROM ProductPhoto p where p.car.id = :id", ProductPhoto.class)
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