package store.bizscanner.dto.response.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScrapResponse {
    private String careaCode;
    private String careaName;
    private String jcategoryCode;
    private LocalDateTime createdAt;
}
