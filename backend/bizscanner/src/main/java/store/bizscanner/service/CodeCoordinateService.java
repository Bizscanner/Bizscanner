package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.bizscanner.dto.response.jcategoryrecommend.CodeAreaPolygonAndCenterResponse;
import store.bizscanner.entity.CodeCenterCoordinate;
import store.bizscanner.entity.CodeCoordinate;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.CodeCenterCoordinateRepository;
import store.bizscanner.repository.CodeCoordinateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeCoordinateService {

    private final CodeCenterCoordinateRepository codeCenterCoordinateRepository;
    private final CodeCoordinateRepository codeCoordinateRepository;


    /**
     * 영역 중앙좌표와, 폴리곤 좌표 찾는 메소드
     * @param code 구, 동, 상권 코드
     * @return 영역 중앙 좌표, 영역 폴리곤 좌표
     */
    public CodeAreaPolygonAndCenterResponse getCodeAreaPolygonAndCenter(String code) {
        //해당 영역 중심 위도,경도
        CodeCenterCoordinate center = codeCenterCoordinateRepository.findById(code)
                .orElseThrow(() -> new CustomException(ErrorCode.AREA_NOT_FOUND));

        //해당 영역 polygon 그리기 위한 좌표들
        List<CodeCoordinate> polygon = codeCoordinateRepository.findByCode(code);

        return new CodeAreaPolygonAndCenterResponse(center, polygon);
    }
}
