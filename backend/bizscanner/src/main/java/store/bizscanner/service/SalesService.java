package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.salesResponse.BestSalesResponse;
import store.bizscanner.dto.response.salesResponse.QuarterSalesCountListResponse;
import store.bizscanner.dto.response.salesResponse.QuarterSalesCountResponse;
import store.bizscanner.entity.Sales;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.SalesRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    /**
     * Best 매출
     * @param careaCode
     * @return 해당 상권에서 Best 매출 관련 정보 반환 (성별, 나이, 요일, 시간, 업종)
     */
    public BestSalesResponse getBestSales(String careaCode) {
        String bestSalesGender = salesRepository.findBestSalesGender(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
        String bestSalesAge = salesRepository.findBestSalesAge(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
        String bestSalesDay = salesRepository.findBestSalesDay(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
        String bestSalesTime = salesRepository.findBestSalesTime(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
        String bestJcategoryName = salesRepository.findTopByCareaCodeOrderByQuarterSalesAmountDesc(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND))
                .getJcategoryName();

        return new BestSalesResponse(bestSalesGender, bestSalesAge, bestSalesDay, bestSalesTime, bestJcategoryName);
    }

    public QuarterSalesCountListResponse getQuarterSalesCount(String careaCode, String jcategoryCode) {
        List<Sales> quarterSalesCountList = salesRepository.findByCareaCodeAndJcategoryCodeOrderByYearCodeAscQuarterCodeAsc(careaCode, jcategoryCode);

        return new QuarterSalesCountListResponse(quarterSalesCountList.stream()
                .map(currQuarterSalesCount -> new QuarterSalesCountResponse(
                        currQuarterSalesCount.getYearCode(),
                        currQuarterSalesCount.getQuarterCode(),
                        currQuarterSalesCount.getQuarterSalesCount()))
                .collect(Collectors.toList()));
    }
}
