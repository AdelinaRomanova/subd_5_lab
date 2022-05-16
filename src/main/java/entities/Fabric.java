package entities;

import javax.persistence.*;

@Entity
@Table(name = "fabric")
public class Fabric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fabric_id")
    private int fabric_id;
    @Column(name = "fabric_name")
    private String fabric_name;
    @Column(name = "contact_fio")
    private String contact_fio;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    public Fabric() {
    }

    public Fabric(String fabric_name, String contact_fio, String address, String phone) {
        this.fabric_name = fabric_name;
        this.contact_fio = contact_fio;
        this.address = address;
        this.phone = phone;
    }

    public int getFabric_id() {
        return fabric_id;
    }

    public String getFabric_name() {
        return fabric_name;
    }
    public void setFabric_name(String fabric_name) {
        this.fabric_name = fabric_name;
    }

    public String getContact_fio() {
        return contact_fio;
    }
    public void setContact_fio(String contact_fio) {
        this.contact_fio = contact_fio;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){ return String.format("%-30d%-30s%-30s%-30s%-30s ", fabric_id, fabric_name,
            contact_fio, address, phone); }
}

