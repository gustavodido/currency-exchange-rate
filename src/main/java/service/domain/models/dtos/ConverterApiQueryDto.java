package service.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

import static java.lang.Double.valueOf;

@Data
@Builder
public class ConverterApiQueryDto {
    private double value;

    @JsonProperty("EUR_USD")
    private void unpackNameFromNestedObject(Map<String, String> result) {
        value = valueOf(result.get("val"));
    }
}
