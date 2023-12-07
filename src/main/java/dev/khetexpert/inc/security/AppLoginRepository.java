// AppLoginRepository Class

package dev.khetexpert.inc.security;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppLoginRepository extends MongoRepository<AppLoginEntity, String> {

    AppLoginEntity findByEmail(String email);

}
