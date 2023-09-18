package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.dto.response.jcategoryrecommend.JcategoryRecommendResponse;
import store.bizscanner.service.JcategoryRecommendConstantService;

@RestController
@CrossOrigin("*")
@RequestMapping("/jcategory-recommend")
@RequiredArgsConstructor
public class JcategoryRecommendController {
    private final JcategoryRecommendConstantService jcategoryRecommendConstantService;

    @GetMapping("/{careaCode}")
    public ResponseEntity<JcategoryRecommendResponse> getJcategoryRecommend(@PathVariable String careaCode){
        return new ResponseEntity<>(jcategoryRecommendConstantService.getJcategoryRecommend(careaCode), HttpStatus.OK);
    }
}
