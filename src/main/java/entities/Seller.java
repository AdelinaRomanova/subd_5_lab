package entities;

import javax.persistence.*;

@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private int seller_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "patronymic")
    private String patronymic;

    public Seller() {
    }

    public Seller(String first_name, String last_name, String patronymic) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString(){ return String.format("%-25d%-25s%-25s%-25s", seller_id, first_name, last_name, patronymic); }
}
