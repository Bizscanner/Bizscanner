package store.bizscanner.dto.response.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ScrapResponses {
    List<ScrapResponse> scrapResponses;
}
