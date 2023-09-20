package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import store.bizscanner.dto.request.CareaRecommendRequest;
import store.bizscanner.dto.response.carearecommend.CareaRecommendResponse;
import store.bizscanner.service.CareaRecommendService;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CareaRecommendController {
    private final CareaRecommendService careaRecommendService;

    @PostMapping("/carea-recommend")
    public ResponseEntity<CareaRecommendResponse> getCarearecommend(@RequestBody CareaRecommendRequest request) {
        return new ResponseEntity<>(careaRecommendService.getCareaRecommend(request), HttpStatus.OK);
    }
}
