package com.personal.project.ecomerce_backend.service;

import com.personal.project.ecomerce_backend.model.LocalUser;
import com.personal.project.ecomerce_backend.model.WebOrder;
import com.personal.project.ecomerce_backend.model.dao.WebOrderDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private WebOrderDAO webOrderDAO;

    public OrderService(WebOrderDAO webOrderDAO) {
        this.webOrderDAO = webOrderDAO;
    }

    public List<WebOrder> getOrders(LocalUser user) {
        return webOrderDAO.findByUser(user);
    }
}
