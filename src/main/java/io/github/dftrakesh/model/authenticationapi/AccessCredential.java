package io.github.dftrakesh.model.authenticationapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessCredential {
    private String accessToken;
    private String requestToken;
    private String storeDomain;
    private String consumerKey;
    private String consumerSecret;
    private String oauthToken;
    private String oauthTokenSecret;
}