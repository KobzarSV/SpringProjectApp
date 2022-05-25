package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.UsersDao;
import ua.goit.model.dto.UsersDto;

@Service
public class UsersConverter implements Converter<UsersDao, UsersDto> {

    private RoleConverter roleConverter;

    public UsersConverter(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public UsersDto toDto(UsersDao dao) {
        UsersDto dto = new UsersDto();
        dto.setId(dao.getId());
        dto.setEmail(dao.getEmail());
        dto.setPassword(dao.getPassword());
        dto.setName(dao.getFirstName() + " " + dao.getLastName());
        dto.setRoleDao(dao.getRoleDao());
        return dto;
    }

    @Override
    public UsersDao toDao(UsersDto dto) {
       UsersDao dao = new UsersDao();
       dao.setId(dto.getId());
       dao.setEmail(dto.getEmail());
       dao.setPassword(dto.getPassword());
       dao.setFirstName(dto.getName().split("\\s")[0]);
       dao.setLastName(dto.getName().substring(dto.getName().lastIndexOf(" ") + 1));
       dao.setRoleDao(dto.getRoleDao());
       return dao;
    }
}
