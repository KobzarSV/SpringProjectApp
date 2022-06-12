package ua.goit.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.model.dao.ProductDao;
import ua.goit.model.dto.ProductDto;

@Service
public class ProductConverter implements Converter<ProductDao, ProductDto> {

    private final ManufacturerConverter manufacturerConverter;

    @Autowired
    public ProductConverter(ManufacturerConverter manufacturerConverter) {
        this.manufacturerConverter = manufacturerConverter;
    }

    @Override
    public ProductDto toDto(ProductDao dao) {
        ProductDto dto = new ProductDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setPrice(dao.getPrice());
        dto.setManufacturer(manufacturerConverter.toDto(dao.getManufacturer()));
        return dto;
    }

    @Override
    public ProductDao toDao(ProductDto dto) {
        ProductDao dao = new ProductDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setPrice(dto.getPrice());
        dao.setManufacturer(manufacturerConverter.toDao(dto.getManufacturer()));
        return dao;
    }
}
