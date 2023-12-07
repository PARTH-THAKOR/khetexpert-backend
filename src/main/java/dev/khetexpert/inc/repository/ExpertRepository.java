// ExpertRepository Interface

package dev.khetexpert.inc.repository;

import dev.khetexpert.inc.entity.Expert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends MongoRepository<Expert, String> {

    Expert findByExpertEmailId(String email);

    Expert findByExpertId(String expertId);

}
