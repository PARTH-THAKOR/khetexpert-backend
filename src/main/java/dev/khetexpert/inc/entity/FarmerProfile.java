// Farmer Class

package dev.khetexpert.inc.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FarmerProfile {

    @NotEmpty(message = "name is null")
    private String name;
    @NotEmpty(message = "phoneNumber is null")
    private String phoneNumber;
    private String imgUrl;

    @NotEmpty(message = "state is null")
    private String state;

}
