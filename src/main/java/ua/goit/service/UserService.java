package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.exception.UserEmailAlreadyExistException;
import ua.goit.exception.UserNotFoundException;
import ua.goit.model.UserRole;
import ua.goit.model.UserStatus;
import ua.goit.model.converter.UserConverter;
import ua.goit.model.dto.UserDto;
import ua.goit.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void save(UserDto userDto) {
        validateEmail(userDto);
        userDto.setUserRole(UserRole.ROLE_VISITOR);
        userDto.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(userConverter.toDao(userDto));
    }

    public void validateEmail(UserDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent((email) -> {
                    throw new UserEmailAlreadyExistException("User with email " + email.getEmail() +
                            " already exists!");
                });
    }

    public UserDto loadUserByEmail(String email) {
        return userConverter.toDto(userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s not exist!", email)
                )));
    }

    public UserDto findById(UUID id) {
        return userConverter.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(
                        "User with id - %s does not exist", id))));
    }

    public List<UserDto> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userDao -> userConverter.toDto(userDao))
                .collect(Collectors.toList());
    }

    public void add(UserDto userDto) {
        validateEmail(userDto);
        userRepository.save(userConverter.toDao(userDto));
    }

    public void update(UserDto userDto) {
        validateEmail(userDto);
        userRepository.save(userConverter.toDao(userDto));
    }

    public void delete(UserDto userDto) {
        userRepository.delete(userConverter.toDao(userDto));
    }
}
