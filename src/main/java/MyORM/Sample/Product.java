package MyORM.Sample;

import MyORM.Annotations.Column;
import MyORM.Annotations.ManyToOne;
import MyORM.Annotations.PrimaryKey;
import MyORM.Annotations.Table;

@Table(name = "products")
public class Product {
    @PrimaryKey(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(tableName = "orders", refColumn = "id", columnName = "order_id")
    private Order order;

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", order=" + order + ", price=" + price + "]";
    }

    
}