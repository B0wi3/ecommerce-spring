package com.personal.project.ecomerce_backend.model.dao;

import com.personal.project.ecomerce_backend.model.*;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductsDAO extends ListCrudRepository<Product, Long> {
}
