package io.github.dftrakesh.model.orderapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingAssignment {
    private Shipping shipping;
    private List<OrderItem> items;
}