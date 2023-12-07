// ApiResponse Class

package dev.khetexpert.inc.objects;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ApiResponse {

    private String message;
    private boolean success;
    private final String dateTime = new Date().toString();

}
