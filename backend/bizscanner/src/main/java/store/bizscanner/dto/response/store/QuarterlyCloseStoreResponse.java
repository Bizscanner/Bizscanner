package store.bizscanner.dto.response.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
public class QuarterlyCloseStoreResponse {
    private final Integer[] quarterlyCloseStore;

    public QuarterlyCloseStoreResponse(List<Integer> quarterlyCloseStoreList) {
        quarterlyCloseStore = quarterlyCloseStoreList.toArray(Integer[]::new);
    }
}
