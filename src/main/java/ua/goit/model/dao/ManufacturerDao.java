package ua.goit.model.dao;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "manufacturer")
@Cacheable
public class ManufacturerDao {

    private UUID id;
    private String name;
    private Set<ProductDao> products;

    public ManufacturerDao(String name, Set<ProductDao> products) {
        this.name = name;
        this.products = products;
    }

    public ManufacturerDao(UUID id, String name, Set<ProductDao> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public ManufacturerDao() {
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

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public Set<ProductDao> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDao> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return id +
                "," + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDao that = (ManufacturerDao) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
