package com.personal.project.ecomerce_backend.model.dao;

import com.personal.project.ecomerce_backend.model.*;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrderDAO extends ListCrudRepository<WebOrder, Long> {

    List<WebOrder> findByUser(LocalUser user);

}
