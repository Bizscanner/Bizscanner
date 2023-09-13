package store.bizscanner.dto.response.store;

import lombok.Getter;
import store.bizscanner.repository.mapping.TotalStoreMapping;

import java.util.List;

@Getter
public class QuarterlyStoreResponse {
    private final Integer[] quarterlyStore;

    public QuarterlyStoreResponse(List<TotalStoreMapping> quarterlyStoreList) {
        quarterlyStore = quarterlyStoreList.stream().map(TotalStoreMapping::getStoreCount).toArray(Integer[]::new);
    }
}
