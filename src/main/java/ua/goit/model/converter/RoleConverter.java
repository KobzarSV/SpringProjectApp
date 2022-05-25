package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.RoleDao;
import ua.goit.model.dto.RoleDto;

@Service
public class RoleConverter implements Converter <RoleDao, RoleDto> {

    @Override
    public RoleDto toDto(RoleDao dao) {
        RoleDto dto = new RoleDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        return dto;
    }

    @Override
    public RoleDao toDao(RoleDto dto) {
        RoleDao dao = new RoleDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        return dao;
    }
}
