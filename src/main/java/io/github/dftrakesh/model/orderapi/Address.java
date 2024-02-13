package io.github.dftrakesh.model.orderapi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Address {
    private String addressType;
    private String city;
    private String countryId;
    private Integer customerAddressId;
    private String email;
    private Integer entityId;
    private String firstname;
    private String lastname;
    private Integer parentId;
    private String postcode;
    private String region;
    private String regionCode;
    private Integer regionId;
    private List<String> street;
    private String telephone;
}