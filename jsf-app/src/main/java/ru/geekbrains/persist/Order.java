package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_tbl")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "from Order"),
        @NamedQuery(name = "deleteOrderById", query = "delete from Order p where p.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<Product> products;

    public Order() {
    }

    public Order(Long id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
