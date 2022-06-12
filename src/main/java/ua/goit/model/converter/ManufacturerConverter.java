package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.ManufacturerDao;
import ua.goit.model.dto.ManufacturerDto;

@Service
public class ManufacturerConverter implements Converter<ManufacturerDao, ManufacturerDto> {

    public ManufacturerConverter() {
    }

    @Override
    public ManufacturerDto toDto(ManufacturerDao dao) {
        ManufacturerDto dto = new ManufacturerDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        return dto;
    }

    @Override
    public ManufacturerDao toDao(ManufacturerDto dto) {
        ManufacturerDao dao = new ManufacturerDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        return dao;
    }
}
