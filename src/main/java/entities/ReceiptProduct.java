package entities;

import javax.persistence.*;

@Entity
@Table(name = "receipt_product")
public class ReceiptProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_product_pkey")
    private int receipt_product_pkey;
    @Column(name = "count")
    private int count;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ReceiptProduct() {
    }

    public ReceiptProduct(Receipt receipt, Product product, int count) {
        this.count = count;
        this.receipt = receipt;
        this.product = product;
    }

    public int getReceipt_product_pkey() {
        return receipt_product_pkey;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Receipt getReceipt() {
        return receipt;
    }
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString(){ return String.format("%-15d%-15d%d",
            receipt.getReceipt_id(), product.getProduct_id(), count); }


}
