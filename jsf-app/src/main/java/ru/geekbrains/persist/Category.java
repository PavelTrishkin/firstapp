package ru.geekbrains.persist;

import javax.persistence.*;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "findAllCategory", query = "from Category"),
        @NamedQuery(name = "countAllCategory", query = "select count(*) from Category"),
        @NamedQuery(name = "deleteCategoryById", query = "delete from Category p where p.id = :id")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long id;

    @Column
    String title;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Category() {
    }

    public Category(Long id, String title) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
