package entities;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int type_id;
    @Column(name = "type_name")
    private String type_name;

    public Type() {
    }

    public Type(String type_name) {
        this.type_name = type_name;
    }

    public int getType_id() {
        return type_id;
    }
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_name() {
        return type_name;
    }

    @Override
    public String toString(){ return String.format("%-15d%s", type_id, type_name); }

}
