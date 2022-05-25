package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.ProductDao;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<ProductDao, UUID> {

    Optional<ProductDao> findByName(String name);

    @Query("SELECT product FROM ProductDao product WHERE product.id IN (?1)")
    Set<ProductDao> findByIds(Set<UUID> ids);
}
