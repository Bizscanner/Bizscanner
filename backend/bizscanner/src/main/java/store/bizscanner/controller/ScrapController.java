package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.dto.request.ScrapRequest;
import store.bizscanner.dto.response.scrap.ScrapResponses;
import store.bizscanner.dto.response.scrap.ScrapValidResponse;
import store.bizscanner.entity.Member;
import store.bizscanner.service.ScrapService;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/scrap")
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;

    @PostMapping
    public ResponseEntity<Void> createScrap(@Valid @RequestBody ScrapRequest scrapRequest) {
        Member loginMember = new Member(); // 현재 로그인 한 유저

        scrapService.createScrap(scrapRequest, loginMember);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteScrap(@Valid @RequestBody ScrapRequest scrapRequest) {
        Member loginMember = new Member(); // 현재 로그인 한 유저

        scrapService.deleteScrap(scrapRequest, loginMember);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/valid/{careaCode}/{jcategoryCode}")
    public ResponseEntity<ScrapValidResponse> isScrapped(@PathVariable String careaCode, @PathVariable String jcategoryCode) {
        Member loginMember = new Member(); // 현재 로그인 한 유저

        return new ResponseEntity<>(scrapService.isScrapped(careaCode, jcategoryCode, loginMember), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ScrapResponses> getScrapResponses() {
        Member loginMember = new Member(); // 현재 로그인 한 유저

        return new ResponseEntity<>(scrapService.getScrapResponses(loginMember), HttpStatus.OK);
    }
}
