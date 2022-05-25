package ua.goit.model.dto;

import ua.goit.model.dao.ManufacturerDao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductDto {

    private UUID id;
    private String name;
    private BigDecimal price;
    private ManufacturerDao manufacturer;

    public ProductDto(UUID id, String name, BigDecimal price, ManufacturerDao manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public ProductDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ManufacturerDao getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDao manufacturer) {
        this.manufacturer = manufacturer;
    }
}
