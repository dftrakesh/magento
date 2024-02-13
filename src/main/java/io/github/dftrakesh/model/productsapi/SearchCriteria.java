package io.github.dftrakesh.model.productsapi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchCriteria {
    private List<String> filterGroups;
    private Integer pageSize;
    private Integer currentPage;
}