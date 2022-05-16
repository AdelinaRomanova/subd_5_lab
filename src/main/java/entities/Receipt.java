package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private int receipt_id;
    @Column(name = "date")
    private Date date;

    public Receipt() {
    }

    public Receipt(Date date) {
        this.date = date;
    }

    public int getReceipt_id() {
        return receipt_id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){ return String.format("%-15d%tF", receipt_id, date); }
}
