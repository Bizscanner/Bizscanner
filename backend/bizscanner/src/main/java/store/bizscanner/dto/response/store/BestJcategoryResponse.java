package store.bizscanner.dto.response.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor
@NoArgsConstructor
public class BestJcategoryResponse {

    private String bestStoreCountJcategory;
    private String bestOpenStoreCountJcategory;
    private String bestCloseStoreCountJcategory;
}
