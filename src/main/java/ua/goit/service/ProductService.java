package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.exception.ProductNotFoundException;
import ua.goit.model.converter.ProductConverter;
import ua.goit.model.dto.ProductDto;
import ua.goit.repository.ProductRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public ProductDto findById(UUID id) {
        return productConverter.toDto(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(
                        "Product with id - %s does not exist", id))));
    }

    public ProductDto findByName(String name) {
        return productConverter.toDto(productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException(String.format(
                        "Product with name - %s does not exist", name))));
    }

    public List<ProductDto> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productDao -> productConverter.toDto(productDao))
                .collect(Collectors.toList());
    }

    public void save(ProductDto productDto) {
        if (productRepository.findByName(productDto.getName()).isEmpty()) {
            productRepository.save(productConverter.toDao(productDto));
        } else {
            throw new ProductNotFoundException("This product is already exist!");
        }
    }

    public Set<ProductDto> findByIds(Set<UUID> ids) {
        return productRepository.findByIds(ids)
                .stream()
                .map(product -> productConverter.toDto(product))
                .collect(Collectors.toSet());
    }
}
