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
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderResponse {
    private Integer baseGrandTotal;
    private Integer totalQtyOrdered;
    private Integer totalItemCount;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

    private Integer baseSubtotalInclTax;
    private String state;
    private String status;
    private Integer grandTotal;
    private String incrementId;
    private Integer entityId;
    private String customerEmail;
    private String customerFirstname;
    private String customerLastname;
    private List<OrderItem> items;
    private Address billingAddress;
    private Payment payment;
    private ExtensionAttributes extensionAttributes;
}