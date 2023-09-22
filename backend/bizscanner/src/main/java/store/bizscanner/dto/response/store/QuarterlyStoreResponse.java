package store.bizscanner.dto.response.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import store.bizscanner.repository.mapping.TotalStoreMapping;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
public class QuarterlyStoreResponse {
    private final Long[] quarterlyStore;

    public QuarterlyStoreResponse(List<TotalStoreMapping> quarterlyStoreList) {
        quarterlyStore = quarterlyStoreList.stream().map(TotalStoreMapping::getStoreCount).toArray(Long[]::new);
    }
}
