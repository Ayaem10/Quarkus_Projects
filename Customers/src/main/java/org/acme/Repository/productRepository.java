package org.acme.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.acme.Products;
import org.apache.commons.text.WordUtils;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class productRepository implements PanacheRepository<Products>{

    public List<Products> findByName(String productName) {
        return this.list("name", productName);
    }

    public List<Products> findByBrand(String productBrand) {
        return this.list("BrandName",
                productBrand);
    }

    public Optional<Products> update(Products product) {
        final var id = product.getId();
        var savedOpt = this.findByIdOptional(id);
        if (savedOpt.isEmpty()) {
            return Optional.empty();
        }

        var saved = savedOpt.get();
        saved.setName(product.getName());
        saved.setPrice(product.getPrice());
        saved.setBrandName(product.getBrandName());

        return Optional.of(saved);
    }


}
