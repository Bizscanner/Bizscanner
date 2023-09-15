package store.bizscanner.dto.response.store;

import lombok.Getter;

import java.util.List;

@Getter
public class QuarterlyCloseStoreResponse {
    private final Integer[] quarterlyCloseStore;

    public QuarterlyCloseStoreResponse(List<Integer> quarterlyCloseStoreList) {
        quarterlyCloseStore = quarterlyCloseStoreList.toArray(Integer[]::new);
    }
}
