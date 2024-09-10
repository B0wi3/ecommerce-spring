package com.personal.project.ecomerce_backend.service;

import com.personal.project.ecomerce_backend.model.Product;
import com.personal.project.ecomerce_backend.model.dao.ProductsDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductsDAO productsDAO;

    public ProductService(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    public List<Product> getProducts() {
        return productsDAO.findAll();
    }
}
