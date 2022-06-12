package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dto.UserDto;

@Service
public class UserConverter implements Converter<UserDao, UserDto> {

    @Override
    public UserDto toDto(UserDao dao) {
        UserDto dto = new UserDto();
        dto.setId(dao.getId());
        dto.setFirstName(dao.getFirstName());
        dto.setLastName(dao.getLastName());
        dto.setEmail(dao.getEmail());
        dto.setPassword(dao.getPassword());
        dto.setUserRole(dao.getUserRole());
        dto.setUserStatus(dao.getUserStatus());
        return dto;
    }

    @Override
    public UserDao toDao(UserDto dto) {
        UserDao dao = new UserDao();
        dao.setId(dto.getId());
        dao.setFirstName(dto.getFirstName());
        dao.setLastName(dto.getLastName());
        dao.setEmail(dto.getEmail());
        dao.setPassword(dto.getPassword());
        dao.setUserRole(dto.getUserRole());
        dao.setUserStatus(dto.getUserStatus());
        return dao;
    }
}
