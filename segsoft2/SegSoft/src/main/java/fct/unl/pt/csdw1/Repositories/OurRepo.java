package fct.unl.pt.csdw1.Repositories;

import fct.unl.pt.csdw1.Entities.OurEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OurRepo extends CrudRepository<OurEntity, Long> {
    Optional<OurEntity> findByUserName(String userName);
}
