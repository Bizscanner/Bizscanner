package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.store.BestJcategoryResponse;
import store.bizscanner.dto.response.store.QuarterlyStoreResponse;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.StoreRepository;
import store.bizscanner.repository.mapping.TotalStoreMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private static final String CURRENT_YEAR = "2023";
    private static final String QUARTER_YEAR = "2021";
    private static final int REQUIRED_RESULT_COUNT = 5;

    public BestJcategoryResponse bestJcategory(String careaCode) {

        //상권 내 가장 많은 점포수를 보유한 업종 정보
        List<String> maxStoreCounts = storeRepository.findMaxStoreCount(careaCode, CURRENT_YEAR);
        if(maxStoreCounts.isEmpty()) {
            throw new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND);
        }
        String bestStoreCountJcategory = maxStoreCounts.get(0);

        //상권 내 가장 개업을 많이 한 업종 정보
        List<String> maxOpenStoreCounts = storeRepository.findMaxOpenStoreCount(careaCode, CURRENT_YEAR);
        String bestOpenStoreCountJcategory = maxOpenStoreCounts.get(0);

        //상권 내 가장 폐업을 많이 한 업종 정보
        List<String> maxCloseStoreCounts = storeRepository.findMaxCloseStoreCount(careaCode, CURRENT_YEAR);
        String bestCloseStoreCountJcategory = maxCloseStoreCounts.get(0);

        return new BestJcategoryResponse(bestStoreCountJcategory, bestOpenStoreCountJcategory, bestCloseStoreCountJcategory);
    }

    public QuarterlyStoreResponse getQuarterlyStore(String careaCode, String jcategoryCode) {
        List<TotalStoreMapping> quarterlyStore =
                storeRepository.findByCareaCodeAndJcategoryCodeAndYearCodeGreaterThanOrderByStoreIdDesc
                        (careaCode, jcategoryCode, QUARTER_YEAR);
        if(quarterlyStore.size() < REQUIRED_RESULT_COUNT) {
            throw new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND);
        }
        return new QuarterlyStoreResponse(quarterlyStore);
    }

}
