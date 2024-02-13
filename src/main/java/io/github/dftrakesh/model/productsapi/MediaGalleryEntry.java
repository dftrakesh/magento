package io.github.dftrakesh.model.productsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MediaGalleryEntry {
    private String id;
    private String mediaType;
    private Integer position;
    private Boolean disabled;
    private List<String> types;
    private String file;
}