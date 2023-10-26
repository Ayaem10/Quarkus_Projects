package org.acme.Exceptions.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.acme.Products;
import org.acme.Repository.productRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.naming.directory.InvalidAttributesException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
public class ProductService {

    @Inject
    productRepository productRepository;

    public List<Products> getProductsByName(String productName) {

            return productRepository.findByName(productName);
        }
    public List<Products> getProductsByBrand(String brandName) {

        return productRepository.findByBrand(brandName);
    }
    public List<Products> getProductAll() {

        return productRepository.listAll();
    }


    public Optional<Products> findProductById(long id) {
        return productRepository.findByIdOptional(id);
    }

    @Transactional
    public void create(Products product) {
        productRepository.persist(product);
    }

    @Transactional
    public Products Update(long productId, Products product) {
        product.setId(productId);
        return productRepository.update(product).orElseThrow(() -> new InvalidParameterException("Product not found"));
    }

    @Transactional
    public boolean delete(long productId) {
        return productRepository.deleteById(productId);
    }
}