package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.ProductDao;
import ua.goit.model.dto.ProductDto;

@Service
public class ProductConverter implements Converter <ProductDao, ProductDto> {

    public ProductConverter() {
    }

    @Override
    public ProductDto toDto(ProductDao dao) {
        ProductDto dto = new ProductDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setPrice(dao.getPrice());
        dto.setManufacturer(dao.getManufacturer());
        return dto;
    }

    @Override
    public ProductDao toDao(ProductDto dto) {
        ProductDao dao = new ProductDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setPrice(dto.getPrice());
        dao.setManufacturer(dto.getManufacturer());
        return dao;
    }
}
