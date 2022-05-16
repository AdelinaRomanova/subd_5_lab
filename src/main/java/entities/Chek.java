package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "chek")
public class Chek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chek_id")
    private int chek_id;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @Column(name = "date")
    private Date date;

    public Chek() {
    }

    public Chek(Seller seller, Date date) {
        this.seller = seller;
        this.date = date;
    }

    public int getChek_id() {
        return chek_id;
    }

    public Seller getSeller() {
        return seller;
    }
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){ return String.format("%-15d%-15d%tF", chek_id,seller.getSeller_id(), date); }
}
