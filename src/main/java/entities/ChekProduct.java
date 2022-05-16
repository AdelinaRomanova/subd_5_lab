package entities;

import javax.persistence.*;

@Entity
@Table(name = "chek_product")
public class ChekProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chek_product_pkey")
    private int chek_product_pkey;
    @Column(name = "count")
    private int count;
    @ManyToOne
    @JoinColumn(name = "chek_id")
    private Chek chek;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ChekProduct() {
    }

    public ChekProduct(Chek chek, Product product, int count) {
        this.chek = chek;
        this.product = product;
        this.count = count;
    }

    public int getChek_product_pkey() {
        return chek_product_pkey;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Chek getChek() {
        return chek;
    }
    public void setChek(Chek chek) {
        this.chek = chek;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString(){ return String.format("%-15d%-15d%d",
            chek.getChek_id(), product.getProduct_id(), count); }

}
