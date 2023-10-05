package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.jcategoryrecommend.CodeAreaPolygonAndCenterResponse;
import store.bizscanner.dto.response.jcategoryrecommend.DongInfoResponse;
import store.bizscanner.dto.response.jcategoryrecommend.DongResponse;
import store.bizscanner.entity.Carea;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.CareaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CareaService {
    private final CareaRepository careaRepository;
    private final CodeCoordinateService codeCoordinateService;
    /**
     * 다른 서비스에서 carea를 사용하기 위해 호출하는 메소드
     * @param careaCode
     * @return carea 엔티티
     */
    public Carea findByCareaCode(String careaCode){
        return careaRepository.findByCareaCode(careaCode).orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
    }

    /**
     * 동별 상권 리스트 API
     * @param dong 동코드
     * @return 행정동에 포함 된 상권 리스트
     *
     * Cacheable
     * 기존에 검색한 동 리스트는 스프링 캐시에 저장하여 다시 조회할 때 디비를 거치지 않고 반환
     */
    @Cacheable(value = "dong_carea", key = "#dong")
    public DongResponse findByDong(String dong) {
        //해당 동에 있는 상권영역을 불러온다.
        List<Carea> dongCareas = careaRepository.findByDong(dong);

        //동 내에 상권이 없을 경우
        if(dongCareas.isEmpty()) {
            throw new CustomException(ErrorCode.AREA_NOT_FOUND);
        }

        List<DongInfoResponse> dongInfoResponseList = new ArrayList<>();

        //상권 영역별 polygon 과 중심값 좌표를 불러온다
        for(Carea carea : dongCareas) {
            CodeAreaPolygonAndCenterResponse polygonAndCenter = codeCoordinateService.getCodeAreaPolygonAndCenter(carea.getCareaCode());
            DongInfoResponse response = new DongInfoResponse(carea, polygonAndCenter);
            dongInfoResponseList.add(response);
        }

        return new DongResponse(dongInfoResponseList);
    }
}
