package ua.goit.model.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ManufacturerDto {

    private UUID id;
    private String name;
    private Set<ProductDto> products;

    public ManufacturerDto(UUID id, String name, Set<ProductDto> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public ManufacturerDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NotEmpty(message = "Please enter name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
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
        ManufacturerDto that = (ManufacturerDto) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
