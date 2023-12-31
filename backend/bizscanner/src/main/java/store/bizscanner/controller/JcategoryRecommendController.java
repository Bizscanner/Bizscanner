package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.dto.response.jcategoryrecommend.CodeAreaPolygonAndCenterResponse;
import store.bizscanner.dto.response.jcategoryrecommend.DongResponse;
import store.bizscanner.dto.response.jcategoryrecommend.JcategoryRecommendResponse;
import store.bizscanner.service.CareaService;
import store.bizscanner.service.CodeCoordinateService;
import store.bizscanner.service.JcategoryRecommendConstantService;

@RestController
@RequestMapping("/jcategory-recommend")
@RequiredArgsConstructor
public class JcategoryRecommendController {
    private final JcategoryRecommendConstantService jcategoryRecommendConstantService;
    private final CareaService careaService;
    private final CodeCoordinateService codeCoordinateService;

    @GetMapping("/{careaCode}")
    public ResponseEntity<JcategoryRecommendResponse> getJcategoryRecommend(@PathVariable String careaCode){
        return new ResponseEntity<>(jcategoryRecommendConstantService.getJcategoryRecommend(careaCode), HttpStatus.OK);
    }

    @GetMapping("/dong/{dong}")
    public ResponseEntity<DongResponse> findCareaByDong(@PathVariable String dong) {
        return new ResponseEntity<>(careaService.findByDong(dong), HttpStatus.OK);
    }

    @GetMapping("/area/{code}")
    public ResponseEntity<CodeAreaPolygonAndCenterResponse> getCodeAreaPolygonAndCenter(@PathVariable String code) {
        return new ResponseEntity<>(codeCoordinateService.getCodeAreaPolygonAndCenter(code), HttpStatus.OK);
    }
}
