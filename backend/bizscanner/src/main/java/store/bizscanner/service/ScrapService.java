package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.request.ScrapRequest;
import store.bizscanner.dto.response.scrap.ScrapResponse;
import store.bizscanner.dto.response.scrap.ScrapResponses;
import store.bizscanner.dto.response.scrap.ScrapValidResponse;
import store.bizscanner.entity.Scrap;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.ScrapRepository;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final CareaService careaService;
    private final MemberService memberService;

    @Transactional
    public Scrap createScrap(ScrapRequest scrapRequest, String email) {
        return scrapRepository.save(Scrap.builder()
                .member(memberService.findByEmail(email))
                .jcategoryCode(scrapRequest.getJcategoryCode())
                .careaCode(scrapRequest.getCareaCode())
                .build());
    }

    @Transactional
    public void deleteScrap(ScrapRequest scrapRequest, String email) {
        scrapRepository.delete(findScrap(scrapRequest, email));
    }

    public Scrap findScrap(ScrapRequest scrapRequest, String email) {
        return scrapRepository.findByMemberAndCareaCodeAndJcategoryCode(memberService.findByEmail(email),
                        scrapRequest.getCareaCode(), scrapRequest.getJcategoryCode())
                .orElseThrow(() -> new CustomException(ErrorCode.SCRAP_NOT_FOUND));
    }

    public ScrapValidResponse isScrapped(String careaCode, String jcategoryCode, String email) {
        return new ScrapValidResponse(scrapRepository.existsByMemberAndCareaCodeAndJcategoryCode(
                memberService.findByEmail(email), careaCode, jcategoryCode));
    }

    public ScrapResponses getScrapResponses(String email) {
        return new ScrapResponses(scrapRepository.findAllByMember(memberService.findByEmail(email)).stream().map(
                        scrap -> new ScrapResponse(scrap.getCareaCode(),
                                careaService.findByCareaCode(scrap.getCareaCode()).getCareaName(),
                                scrap.getJcategoryCode(),
                                scrap.getCreatedAt()))
                .collect(Collectors.toList()));
    }
}
