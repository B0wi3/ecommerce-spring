package com.personal.project.ecomerce_backend.model;

import jakarta.persistence.*;

public class WebOrderQuantity {
    @Entity
    @Table(name = "web_order_quantities")
    public class WebOrderQuantities {

        /** The unqiue id of the order quantity. */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;
        /** The product being ordered. */
        @ManyToOne(optional = false)
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;
        /** The quantity being ordered. */
        @Column(name = "quantity", nullable = false)
        private Integer quantity;
        /** The order itself. */
        @ManyToOne(optional = false)
        @JoinColumn(name = "order_id", nullable = false)
        private WebOrder order;

        public WebOrder getOrder() {
            return order;
        }

        public void setOrder(WebOrder order) {
            this.order = order;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
}
