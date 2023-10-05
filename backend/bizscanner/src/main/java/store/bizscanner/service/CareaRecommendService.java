package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.bizscanner.dto.request.CareaRecommendRequest;
import store.bizscanner.dto.response.carearecommend.CareaRecommendResponse;
import store.bizscanner.entity.Carea;
import store.bizscanner.entity.CareaRecommendConstant;
import store.bizscanner.entity.CareaRecommendNormalized;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.CareaRecommendConstantRepository;
import store.bizscanner.repository.CareaRecommendNormalizedRepository;

import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;

@Service
@RequiredArgsConstructor
public class CareaRecommendService {

    private final CareaRecommendNormalizedRepository careaRecommendNormalizedRepository;
    private final CareaRecommendConstantRepository careaRecommendConstantRepository;
    private final CareaService careaService;
    private static final Long AVERAGE_RENT_AMOUNT = 67774L;


    public CareaRecommendResponse getCareaRecommend(CareaRecommendRequest request) {
        //추천 상권 선정
        CareaRecommendNormalized careaRecommend = findCareaRecommend(request.getJcategoryCode(), request.getFirstInvestmentAmount(), request.getStoreArea());
        return compareToAverage(careaRecommend, request.getStoreArea());
    }
    /**
     * 업종과 초기 투자금, 면적을 입력받았을 때, 알고리즘을 이용해 최선의 상권을 찾는 메소드
     * @param jcategoryCode 업종 코드
     * @param firstInvestmentAmount 초기 투자금
     * @param storeArea 업장 면적
     */
    public CareaRecommendNormalized findCareaRecommend(String jcategoryCode, long firstInvestmentAmount, long storeArea) {
        //해당 업종이 있는 상권 중 초기 투자금을 만족하는 상권들을 모두 불러온다
        List<CareaRecommendNormalized> careaList = careaRecommendNormalizedRepository.
                findByJcategoryCodeAndFirstInvestmentAmountLessThan(jcategoryCode,firstInvestmentAmount);

        //해당 만족하는 조건의 상권이 없다면, Exception 처리를 해준다
        if(careaList.isEmpty()) {
            throw new CustomException(ErrorCode.RECOMMEND_CAREA_NOT_FOUND);
        }

        //순이익 정규화를 위해 순이익 min,max 를 구해준다.
        LongSummaryStatistics statistics = careaList.stream().
                mapToLong(x -> x.getProfit(storeArea)).summaryStatistics();
        long minProfit = statistics.getMax();
        long maxProfit = statistics.getMin();

        return careaList.stream().max(Comparator.comparingDouble(o -> o.getScore(minProfit, maxProfit, storeArea))).get();
    }

    /**
     * 상권이 정해졌을 때, 평균과 비교하여 반환하는 response 만드는 메소드
     * @param recommendCarea 추천해줄 상권
     * @param storeArea 업장 면적
     * @return 상권추천 response DTO
     */
    public CareaRecommendResponse compareToAverage(CareaRecommendNormalized recommendCarea, long storeArea) {
        //업종의 평균 매출액, 매출액 변화율
        CareaRecommendConstant constant = careaRecommendConstantRepository.
                findByJcategoryCode(recommendCarea.getJcategoryCode())
                .orElseThrow(() -> new CustomException(ErrorCode.RECOMMEND_CAREA_NOT_FOUND));

        //업종의 평균 추정 순이익
        Long averageNetProfitByJcategory = constant.getSalesAmount() - AVERAGE_RENT_AMOUNT * storeArea;
        //해당 업종,상권의 추정 순이익
        Long recommendedNetProfit = recommendCarea.getProfit(storeArea);
        //업종의 평균 매출액 변화율
        Double averageSalesAmountRateByJcategory = constant.getSalesAmountRate();
        //상권 코드 바탕으로 상권 명 불러오기
        Carea carea = careaService.findByCareaCode(recommendCarea.getCareaCode());

        return new CareaRecommendResponse(carea.getCareaName(), averageNetProfitByJcategory, recommendedNetProfit, averageSalesAmountRateByJcategory, recommendCarea);
    }
}
