package store.bizscanner.dto.response.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class BestJcategoryResponse {

    private String bestStoreCountJcategory;
    private String bestOpenStoreCountJcategory;
    private String bestCloseStoreCountJcategory;
}
