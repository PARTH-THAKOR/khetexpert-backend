// ExpertProfileService Class

package dev.khetexpert.inc.service;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.entity.Expert;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.repository.ExpertRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpertProfileService {

    private ExpertRepository expertRepository;

    public ResponseEntity<Expert> getExpert(String expertId) {
        Expert expert = expertRepository.findByExpertId(expertId);
        if (expert != null) {
            return new ResponseEntity<>(expert, HttpStatus.OK);
        } else {
            throw new KhetExpertError("Expert Not Found");
        }
    }

    public ResponseEntity<ApiResponse> updateExpert(String emailId, Expert expert) {
        Expert expert1 = expertRepository.findByExpertEmailId(emailId);
        if (expert1 != null) {
            expert1.setExpertName(expert.getExpertName());
            expert1.setExpertMobileNumber(expert.getExpertMobileNumber());
            expert1.setImageUrl(expert.getImageUrl());
            expertRepository.save(expert1);
            return new ResponseEntity<>(ApiResponse.builder().success(true).message("Profile Updated").build(), HttpStatus.CREATED);
        } else {
            throw new KhetExpertError("Expert Not Found");
        }
    }

}
