package ua.goit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.dao.ManufacturerDao;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ManufacturerRepository extends CrudRepository<ManufacturerDao, UUID> {

    Optional<ManufacturerDao> findByName(String name);

    @Query("SELECT manufacturer FROM ManufacturerDao manufacturer WHERE manufacturer.id IN (?1)")
    List<ManufacturerDao> findByIds(Set<UUID> ids);
}
