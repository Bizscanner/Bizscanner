package store.bizscanner.dto.response.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
public class QuarterlyOpenStoreResponse {
    private final Integer[] quarterlyOpenStore;

    public QuarterlyOpenStoreResponse(List<Integer> quarterlyOpenStoreList) {
        quarterlyOpenStore = quarterlyOpenStoreList.toArray(Integer[]::new);
    }
}
