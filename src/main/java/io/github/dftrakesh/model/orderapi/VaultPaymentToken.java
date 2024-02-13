package io.github.dftrakesh.model.orderapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VaultPaymentToken {
    private String publicHash;
    private String paymentMethodCode;
    private String type;
    private String gatewayToken;
    private String tokenDetails;
    private Boolean isActive;
    private Boolean isVisible;
}