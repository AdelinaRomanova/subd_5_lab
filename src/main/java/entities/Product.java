package entities;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type types;
    @ManyToOne
    @JoinColumn(name = "fabric_id")
    private Fabric fabrics;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "price")
    private int price;
    @Column(name = "initial_price")
    private int initial_price;
    @Column(name = "size")
    private int size;
    @Column(name = "count")
    private int count;

    public Product() {
    }

    public Product(Type types, Fabric fabrics, String product_name, int price, int initial_price, int size, int count) {
        this.types = types;
        this.fabrics = fabrics;
        this.product_name = product_name;
        this.price = price;
        this.initial_price = initial_price;
        this.size = size;
        this.count = count;
    }

    public int getProduct_id() {
        return product_id;
    }

    public Type getTypes() {
        return types;
    }
    public void setTypes(Type types) {
        this.types = types;
    }

    public Fabric getFabrics() {
        return fabrics;
    }
    public void setFabrics(Fabric fabrics) {
        this.fabrics = fabrics;
    }

    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getInitial_price() {
        return initial_price;
    }
    public void setInitial_price(int initial_price) {
        this.initial_price = initial_price;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString(){ return String.format("%-30d%-30d%-30d%-30s%-30d%-30d%-30d%-30d",
            product_id, types.getType_id(),fabrics.getFabric_id(), product_name, price, initial_price,
            size, count); }

}
