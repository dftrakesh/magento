package io.github.dftrakesh.model.productsapi;

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
public class Products {
    private Integer id;
    private String sku;
    private String name;
    private Integer attributeSetId;
    private Integer price;
    private Integer status;
    private Integer visibility;
    private String typeId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

    private ExtensionAttributes extensionAttributes;
    private List<MediaGalleryEntry> mediaGalleryEntries;
}