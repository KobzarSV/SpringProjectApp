package ua.goit.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.model.dao.ManufacturerDao;
import ua.goit.model.dto.ManufacturerDto;

import java.util.stream.Collectors;

@Service
public class ManufacturerConverter implements Converter <ManufacturerDao, ManufacturerDto> {

    private final ProductConverter productConverter;

    @Autowired
    public ManufacturerConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Override
    public ManufacturerDto toDto(ManufacturerDao dao) {
        ManufacturerDto dto = new ManufacturerDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setProducts(dao.getProducts().stream()
                .map(productConverter::toDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public ManufacturerDao toDao(ManufacturerDto dto) {
        ManufacturerDao dao = new ManufacturerDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setProducts(dto.getProducts().stream()
                .map(productConverter::toDao)
                .collect(Collectors.toSet()));
        return dao;
    }
}
