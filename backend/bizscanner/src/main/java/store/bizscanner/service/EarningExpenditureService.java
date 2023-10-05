package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.bizscanner.dto.response.earningexpenditure.ConsumptionTrendResponse;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.EarningExpenditureRepository;

@Service @RequiredArgsConstructor
public class EarningExpenditureService {

    private final EarningExpenditureRepository earningExpenditureRepository;

    public ConsumptionTrendResponse getConsumptionTrend(String careaCode) {
        return earningExpenditureRepository.getConsumptionTrend(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
    }
}
