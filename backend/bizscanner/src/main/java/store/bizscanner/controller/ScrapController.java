package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.dto.request.ScrapRequest;
import store.bizscanner.dto.response.scrap.ScrapResponses;
import store.bizscanner.dto.response.scrap.ScrapValidResponse;
import store.bizscanner.service.ScrapService;

import javax.validation.Valid;

@RestController
@RequestMapping("/scrap")
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;

    @PostMapping
    public ResponseEntity<Void> createScrap(@Valid @RequestBody ScrapRequest scrapRequest, Authentication authentication) {
        scrapService.createScrap(scrapRequest, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteScrap(@Valid @RequestBody ScrapRequest scrapRequest, Authentication authentication) {
        scrapService.deleteScrap(scrapRequest, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/valid/{careaCode}/{jcategoryCode}")
    public ResponseEntity<ScrapValidResponse> isScrapped(@PathVariable String careaCode, @PathVariable String jcategoryCode, Authentication authentication) {
        return new ResponseEntity<>(scrapService.isScrapped(careaCode, jcategoryCode, authentication.getName()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ScrapResponses> getScrapResponses(Authentication authentication) {
        return new ResponseEntity<>(scrapService.getScrapResponses(authentication.getName()), HttpStatus.OK);
    }
}
