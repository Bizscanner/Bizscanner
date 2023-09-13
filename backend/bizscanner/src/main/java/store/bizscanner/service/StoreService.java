package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.bizscanner.dto.response.store.BestJcategoryResponse;
import store.bizscanner.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private static final String yearCode = "2023";

    public BestJcategoryResponse bestJcategory(String careaCode) {

        //상권 내 가장 많은 점포수를 보유한 업종 정보
        List<String> maxStoreCounts = storeRepository.findMaxStoreCount(careaCode, yearCode);
        String bestStoreCountJcategory = maxStoreCounts.get(0);

        //상권 내 가장 개업을 많이 한 업종 정보
        List<String> maxOpenStoreCounts = storeRepository.findMaxOpenStoreCount(careaCode, yearCode);
        String bestOpenStoreCountJcategory = maxOpenStoreCounts.get(0);

        //상권 내 가장 폐업을 많이 한 업종 정보
        List<String> maxCloseStoreCounts = storeRepository.findMaxCloseStoreCount(careaCode, yearCode);
        String bestCloseStoreCountJcategory = maxCloseStoreCounts.get(0);

        return new BestJcategoryResponse(bestStoreCountJcategory, bestOpenStoreCountJcategory, bestCloseStoreCountJcategory);
    }

}