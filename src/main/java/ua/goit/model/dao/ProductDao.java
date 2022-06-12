package ua.goit.model.dao;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@javax.persistence.Cacheable
public class ProductDao {

    private UUID id;
    private String name;
    private BigDecimal price;
    private ManufacturerDao manufacturer;

    public ProductDao(UUID id, String name, BigDecimal price, ManufacturerDao manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public ProductDao() {
    }

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    public ManufacturerDao getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDao manufacturer) {
        this.manufacturer = manufacturer;
    }
}
