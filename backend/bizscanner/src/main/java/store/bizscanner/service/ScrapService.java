package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.request.ScrapRequest;
import store.bizscanner.dto.response.scrap.ScrapResponse;
import store.bizscanner.dto.response.scrap.ScrapResponses;
import store.bizscanner.dto.response.scrap.ScrapValidResponse;
import store.bizscanner.entity.Member;
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

    @Transactional
    public Scrap createScrap(ScrapRequest scrapRequest, Member member) {
        return scrapRepository.save(Scrap.builder()
                .member(member)
                .jcategoryCode(scrapRequest.getJcategoryCode())
                .careaCode(scrapRequest.getCareaCode())
                .build());
    }

    @Transactional
    public void deleteScrap(ScrapRequest scrapRequest, Member member) {
        scrapRepository.delete(findScrap(scrapRequest, member));
    }

    public Scrap findScrap(ScrapRequest scrapRequest, Member member) {
        return scrapRepository.findByMemberAndCareaCodeAndJcategoryCode(member,
                        scrapRequest.getCareaCode(), scrapRequest.getJcategoryCode())
                .orElseThrow(() -> new CustomException(ErrorCode.SCRAP_NOT_FOUND));
    }

    public ScrapValidResponse isScrapped(String careaCode, String jcategoryCode, Member member) {
        return new ScrapValidResponse(scrapRepository.existsByMemberAndCareaCodeAndJcategoryCode(
                member, careaCode, jcategoryCode));
    }

    public ScrapResponses getScrapResponses(Member member) {
        return new ScrapResponses(scrapRepository.findAllByMember(member).stream().map(
                        scrap -> new ScrapResponse(scrap.getCareaCode(),
                                careaService.findByCareaCode(scrap.getCareaCode()).getCareaName(),
                                scrap.getJcategoryCode(),
                                scrap.getCreatedAt()))
                .collect(Collectors.toList()));
    }
}
