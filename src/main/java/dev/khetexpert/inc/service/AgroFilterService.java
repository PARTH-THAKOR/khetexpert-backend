// AgroFilterService

package dev.khetexpert.inc.service;

import lombok.AllArgsConstructor;
import dev.khetexpert.inc.constants.Constants;
import dev.khetexpert.inc.entity.AgroFilter;
import dev.khetexpert.inc.exception.KhetExpertError;
import dev.khetexpert.inc.objects.ApiResponse;
import dev.khetexpert.inc.repository.AgroFilterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AgroFilterService {

    private AgroFilterRepository agroFilterRepository;

    public ResponseEntity<ApiResponse> addAgriWords(AgroFilter agroFilter) {
        AgroFilter repository = agroFilterRepository.findByFilterAuthToken(Constants.Filter_AUTH_TOKEN);
        List<String> agriWords = repository.getAgriWords();
        agriWords.addAll(0, agroFilter.getAgriWords());
        repository.setAgriWords(agriWords);
        agroFilterRepository.save(repository);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Added").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> addFertilizers(AgroFilter agroFilter) {
        AgroFilter repository = agroFilterRepository.findByFilterAuthToken(Constants.Filter_AUTH_TOKEN);
        List<String> fertilizers = repository.getFertilizers();
        fertilizers.addAll(0, agroFilter.getFertilizers());
        repository.setFertilizers(fertilizers);
        agroFilterRepository.save(repository);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Added").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> addVegetables(AgroFilter agroFilter) {
        AgroFilter repository = agroFilterRepository.findByFilterAuthToken(Constants.Filter_AUTH_TOKEN);
        List<String> vegetables = repository.getVegetables();
        vegetables.addAll(0, agroFilter.getVegetables());
        repository.setVegetables(vegetables);
        agroFilterRepository.save(repository);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Added").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> addFruits(AgroFilter agroFilter) {
        AgroFilter repository = agroFilterRepository.findByFilterAuthToken(Constants.Filter_AUTH_TOKEN);
        List<String> fruits = repository.getFruits();
        fruits.addAll(0, agroFilter.getFruits());
        repository.setFruits(fruits);
        agroFilterRepository.save(repository);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Added").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> addDisease(AgroFilter agroFilter) {
        AgroFilter repository = agroFilterRepository.findByFilterAuthToken(Constants.Filter_AUTH_TOKEN);
        List<String> disease = repository.getDiseases();
        disease.addAll(0, agroFilter.getDiseases());
        repository.setDiseases(disease);
        agroFilterRepository.save(repository);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("Added").build(), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse> filter(String prompt) {
        String lowerPrompt = prompt.toLowerCase();
        String[] promptWords = lowerPrompt.split(" ");
        AgroFilter agroFilter = agroFilterRepository.findByFilterAuthToken(Constants.Filter_AUTH_TOKEN);
        if (agroFilter != null) {
            int trueCount = 0;
            String fruitsString = agroFilter.getFruits().toString().replace(" ", "").toLowerCase();
            String diseaseString = agroFilter.getDiseases().toString().replace(" ", "").toLowerCase();
            String vegetablesString = agroFilter.getVegetables().toString().replace(" ", "").toLowerCase();
            String agriwordsString = agroFilter.getAgriWords().toString().replace(" ", "").toLowerCase();
            String fertilizersString = agroFilter.getFertilizers().toString().replace(" ", "").toLowerCase();
            List<Boolean> result = new ArrayList<>();
            for (String word : promptWords) {
                if (word.length() > 3) {
                    result.add(fruitsString.contains(word));
                    result.add(diseaseString.contains(word));
                    result.add(vegetablesString.contains(word));
                    result.add(agriwordsString.contains(word));
                    result.add(fertilizersString.contains(word));
                }
            }
            for (Boolean bool : result) {
                if (bool) {
                    trueCount++;
                }
            }
            double filterResult = ((double) trueCount / result.size()) * 100;
            if (filterResult > 10) {
                return new ResponseEntity<>(ApiResponse.builder().success(true).message("Agricultural Prompt").build(), HttpStatus.OK);
            } else {
                throw new KhetExpertError("Non Agricultural Prompt");
            }
        } else {
            throw new KhetExpertError("Word Filter Not Find");
        }
    }

}
