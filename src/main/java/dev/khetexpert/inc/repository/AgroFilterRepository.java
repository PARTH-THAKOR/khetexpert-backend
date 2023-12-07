// AgroFilterRepository

package dev.khetexpert.inc.repository;

import dev.khetexpert.inc.entity.AgroFilter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgroFilterRepository extends MongoRepository<AgroFilter, String> {

    AgroFilter findByFilterAuthToken(String filterAuthToken);

}
