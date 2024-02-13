package io.github.dftrakesh.model.orderapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Payment {
    private String accountStatus;
    private String additionalData;
    private Integer amountOrdered;
    private Integer baseAmountOrdered;
    private Integer baseShippingAmount;
    private String ccExpYear;
    private String ccLast4;
    private String ccSsStartMonth;
    private String ccSsStartYear;
    private Integer entityId;
    private String method;
    private Integer parentId;
    private Integer shippingAmount;
    private ExtensionAttributes extensionAttributes;
}