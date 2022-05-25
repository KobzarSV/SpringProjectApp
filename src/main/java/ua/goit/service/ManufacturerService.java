package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.exception.ManufacturerNotFoundException;
import ua.goit.model.converter.ManufacturerConverter;
import ua.goit.model.dto.ManufacturerDto;
import ua.goit.repository.ManufacturerRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerConverter manufacturerConverter;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository, ManufacturerConverter manufacturerConverter) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerConverter = manufacturerConverter;
    }

    public ManufacturerDto findById (UUID uuid) {
        return manufacturerConverter.toDto(manufacturerRepository.findById(uuid)
                .orElseThrow(() -> new ManufacturerNotFoundException(String.format(
                        "Manufacturer with name - %s does not exist", uuid))));
    }

    public ManufacturerDto findByName(String name) {
        return manufacturerConverter.toDto(manufacturerRepository.findByName(name)
                .orElseThrow(() -> new ManufacturerNotFoundException(String.format(
                        "Manufacturer with name - %s does not exist", name))));
    }

    public List<ManufacturerDto> findAll() {
        return StreamSupport.stream(manufacturerRepository.findAll().spliterator(), false)
                .map(ManufacturerDao -> manufacturerConverter.toDto(ManufacturerDao))
                .collect(Collectors.toList());
    }

    public void save(ManufacturerDto manufacturerDto) {
        if (manufacturerRepository.findByName(manufacturerDto.getName()).isEmpty()) {
            manufacturerRepository.save(manufacturerConverter.toDao(manufacturerDto));
        } else {
            throw new ManufacturerNotFoundException("This manufacturer is already exist!");
        }
    }

    public Set<ManufacturerDto> findByIds(Set<UUID> uuids) {
        return manufacturerRepository.findByIds(uuids)
                .stream()
                .map(manufacturer -> manufacturerConverter.toDto(manufacturer))
                .collect(Collectors.toSet());
    }
}
