// ExpertOTPRepository Interface

package dev.khetexpert.inc.repository;

import dev.khetexpert.inc.entity.ExpertOTPs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertOTPRepository extends MongoRepository<ExpertOTPs, String> {

    ExpertOTPs findByEmail(String email);

}
