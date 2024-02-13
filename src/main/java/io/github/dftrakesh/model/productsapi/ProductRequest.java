package io.github.dftrakesh.model.productsapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRequest {
    private Integer id;
    private String sku;
    private String name;
    private Integer attributeSetId;
    private Integer price;
    private Integer status;
    private Integer visibility;
    private ExtensionAttributes extensionAttributes;
}