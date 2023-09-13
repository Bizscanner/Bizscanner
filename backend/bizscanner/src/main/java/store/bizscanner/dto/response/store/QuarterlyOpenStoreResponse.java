package store.bizscanner.dto.response.store;

import lombok.Getter;

import java.util.List;

@Getter
public class QuarterlyOpenStoreResponse {
    private final Integer[] quarterlyOpenStore;

    public QuarterlyOpenStoreResponse(List<Integer> quarterlyOpenStoreList) {
        quarterlyOpenStore = quarterlyOpenStoreList.toArray(Integer[]::new);
    }
}
