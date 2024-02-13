package io.github.dftrakesh.model.orderapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.dftrakesh.model.common.LocalDateTimeDeserializer;
import io.github.dftrakesh.model.common.LocalDateTimeSerializer;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderItem {
    private Integer basePrice;
    private Integer basePriceInclTax;
    private Integer price;
    private Integer priceInclTax;
    private Integer qtyCanceled;
    private Integer qtyInvoiced;
    private Integer qtyOrdered;
    private Integer qtyRefunded;
    private Integer qtyShipped;
    private Integer itemId;
    private String name;
    private Integer orderId;
    private String sku;
    private Integer productId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;
}