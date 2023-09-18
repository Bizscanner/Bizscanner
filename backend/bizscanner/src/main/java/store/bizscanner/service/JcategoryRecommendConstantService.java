package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.jcategoryrecommend.JcategoryRecommendResponse;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.JcategoryRecommendConstantRepository;
import store.bizscanner.repository.SalesRepository;
import store.bizscanner.repository.StoreRepository;
import store.bizscanner.repository.mapping.JcategoryRecommendMapping;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JcategoryRecommendConstantService {
    private final JcategoryRecommendConstantRepository jcategoryRecommendConstantRepository;
    private final SalesRepository salesRepository;
    private final StoreRepository storeRepository;

    private static final int RECOMMEND_MAX_CLOSE_RATING = 15;

    /**
     * 업종 추천
     * @param careaCode
     * @return 업종 추천 결과 및 추천 근거
     *
     * @value recommendList : 해당 상권의 모든 업종의 예상 매출액 (내림차순)
     * @value closeRate : 폐업률
     * 폐업률이 15이하이면서 예상 매출액이 가장 높은 업종을 추천
     * 추천 근거를 함께 제공하기 위해 해당 상권의 전체 업종의 매출액 정보를 함께 반환
     * 리스트의 모든 객체가 조건을 만족하지 않을 경우 추천 업종이 없다고 판단 -> RECOMMEND_JCATEGORY_NOT_FOUND
     */
    public JcategoryRecommendResponse getJcategoryRecommend(String careaCode){
        List<JcategoryRecommendMapping> recommendList = salesRepository.getJcategoryRecommend(careaCode);

        if(recommendList.isEmpty()){
            throw new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND);
        }

        for(JcategoryRecommendMapping jcategoryRecommendMapping : recommendList){
            Integer closeRate = storeRepository.findTopByCareaCodeAndJcategoryCodeOrderByYearCodeDescQuarterCodeDesc(careaCode, jcategoryRecommendMapping.getJcategoryCode())
                    .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND)).getCloseRate();

            if(closeRate < RECOMMEND_MAX_CLOSE_RATING){
                return new JcategoryRecommendResponse(jcategoryRecommendMapping,
                        jcategoryRecommendConstantRepository.findByCareaCode(careaCode)
                                .orElseThrow(()->new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND)),
                        closeRate);
            }
        }
        throw new CustomException(ErrorCode.RECOMMEND_JCATEGORY_NOT_FOUND);
    }
}
