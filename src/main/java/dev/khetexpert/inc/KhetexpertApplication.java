/**
 * @Project(KhetExpert.backend)
 * @Component(KhetExpert.backend)
 * @Developer(PARTH-THAKOR)
 * @Company(com.khetexpert.inc)
 * @Copyrighted(©2023.AllRightsReserved)
 **/

package dev.khetexpert.inc;

import dev.firebase.FirebaseApplication;
import dev.khetexpert.inc.objects.Admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Admin
@EnableCaching
@EnableFeignClients
@FirebaseApplication
@SpringBootApplication
@EnableMongoRepositories
public class KhetexpertApplication {

    @Admin
    public static void main(String[] args) {
        SpringApplication.run(KhetexpertApplication.class, args);
        System.out.println("welcomes you !!");
    }

}
