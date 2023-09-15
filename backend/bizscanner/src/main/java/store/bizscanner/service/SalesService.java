package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.sales.*;
import store.bizscanner.entity.Sales;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.SalesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        if (quarterSalesCountList.size() < 2) {
            throw new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND);
        }

        return new QuarterSalesCountListResponse(quarterSalesCountList.stream()
                .map(currQuarterSalesCount -> new QuarterSalesCountResponse(
                        currQuarterSalesCount.getYearCode(),
                        currQuarterSalesCount.getQuarterCode(),
                        currQuarterSalesCount.getQuarterSalesCount()))
                .collect(Collectors.toList()));
    }

    public SalesAmountResponse getSalesAmount(String careaCode, String jcategoryCode) {
        // 분기별 매출액 데이터
        List<Long> quarterlySalesAmountResponseList = salesRepository.findByCareaCodeAndJcategoryCodeOrderByYearCodeAscQuarterCodeAsc(careaCode, jcategoryCode)
                .stream()
                .map(currQuarterlyAmount -> currQuarterlyAmount.getQuarterSalesAmount())
                .collect(Collectors.toList());

        if (quarterlySalesAmountResponseList.size() < 2) {
            throw new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND);
        }

        Sales selectedSalesData = salesRepository.findTopByCareaCodeAndJcategoryCode(careaCode, jcategoryCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));;

        // 요일별 매출액 데이터
        List<Long> daySalesAmountResponseList = new ArrayList<>();

        daySalesAmountResponseList.add(selectedSalesData.getMondaySalesAmount());
        daySalesAmountResponseList.add(selectedSalesData.getTuesdaySalesAmount());
        daySalesAmountResponseList.add(selectedSalesData.getWednesdaySalesAmount());
        daySalesAmountResponseList.add(selectedSalesData.getThursdaySalesAmount());
        daySalesAmountResponseList.add(selectedSalesData.getFridaySalesAmount());
        daySalesAmountResponseList.add(selectedSalesData.getSaturdaySalesAmount());
        daySalesAmountResponseList.add(selectedSalesData.getSundaySalesAmount());


        // 시간별 매출액 데이터
        List<Long> timeSalesAmountResponseList = new ArrayList<>();
        timeSalesAmountResponseList.add(selectedSalesData.getTime1SalesAmount());
        timeSalesAmountResponseList.add(selectedSalesData.getTime2SalesAmount());
        timeSalesAmountResponseList.add(selectedSalesData.getTime3SalesAmount());
        timeSalesAmountResponseList.add(selectedSalesData.getTime4SalesAmount());
        timeSalesAmountResponseList.add(selectedSalesData.getTime5SalesAmount());
        timeSalesAmountResponseList.add(selectedSalesData.getTime6SalesAmount());

        // 성별 매출액 데이터 (male, female 순서)
        List<Long> genderSalesAmountResponseList = new ArrayList<>();
        genderSalesAmountResponseList.add(selectedSalesData.getMaleSalesAmount());
        genderSalesAmountResponseList.add(selectedSalesData.getFemaleSalesAmount());

        // 나이별 매출액 데이터
        List<Long> ageSalesAmountResponseList = new ArrayList<>();
        ageSalesAmountResponseList.add(selectedSalesData.getTeensSalesAmount());
        ageSalesAmountResponseList.add(selectedSalesData.getTwentiesSalesAmount());
        ageSalesAmountResponseList.add(selectedSalesData.getThirtiesSalesAmount());
        ageSalesAmountResponseList.add(selectedSalesData.getFortiesSalesAmount());
        ageSalesAmountResponseList.add(selectedSalesData.getFiftiesSalesAmount());
        ageSalesAmountResponseList.add(selectedSalesData.getSixtiesSalesAmount());


        return new SalesAmountResponse(quarterlySalesAmountResponseList, daySalesAmountResponseList, timeSalesAmountResponseList, genderSalesAmountResponseList, ageSalesAmountResponseList);
    }


}
