package store.bizscanner.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.request.ScrapRequest;
import store.bizscanner.dto.response.scrap.ScrapResponses;
import store.bizscanner.entity.Member;
import store.bizscanner.entity.Scrap;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ScrapServiceTest {
    @Autowired
    private ScrapService scrapService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createScrap() {
        // given
        Member member = new Member();
        memberRepository.save(member);

        String careaCode = "2110008";
        String jcategoryCode = "CS300002";
        ScrapRequest scrapRequest = new ScrapRequest(careaCode, jcategoryCode);

        // when
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member.getEmail());

        // then
        assertThat(scrapService.findScrap(scrapRequest, member.getEmail())).isEqualTo(savedScrap);
    }

    @Test
    void deleteScrap() {
        // given
        Member member = new Member();
        memberRepository.save(member);

        String careaCode = "2110008";
        String jcategoryCode = "CS300002";
        ScrapRequest scrapRequest = new ScrapRequest(careaCode, jcategoryCode);

        // when
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member.getEmail());
        scrapService.deleteScrap(scrapRequest, member.getEmail());

        // then
        assertThatThrownBy(() -> scrapService.findScrap(scrapRequest, member.getEmail()))
                .isInstanceOf(CustomException.class);
    }

    @Test
    void isScrapped() {
        // given
        Member member = new Member();
        memberRepository.save(member);

        String careaCode = "2110008";
        String jcategoryCode = "CS300002";
        ScrapRequest scrapRequest = new ScrapRequest(careaCode, jcategoryCode);

        // when
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member.getEmail());

        // then
        assertThat(scrapService.isScrapped(careaCode, jcategoryCode, member.getEmail()).isScrapped()).isTrue();
    }

    @Test
    void getScrapResponses() {
        // given
        Member member = new Member();
        memberRepository.save(member);

        String careaCode = "2110008";
        String jcategoryCode = "CS300002";
        ScrapRequest scrapRequest = new ScrapRequest(careaCode, jcategoryCode);

        String careaCode2 = "2110009";
        String jcategoryCode2 = "CS300003";
        ScrapRequest scrapRequest2 = new ScrapRequest(careaCode2, jcategoryCode2);

        // when
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member.getEmail());
        Scrap savedScrap2 = scrapService.createScrap(scrapRequest2, member.getEmail());

        // then
        ScrapResponses scrapResponses = scrapService.getScrapResponses(member.getEmail());

        assertThat(scrapResponses.getScrapResponses().get(0).getCareaCode()).isEqualTo(savedScrap.getCareaCode());
        assertThat(scrapResponses.getScrapResponses().get(1).getCareaCode()).isEqualTo(savedScrap2.getCareaCode());
        assertThat(scrapResponses.getScrapResponses()).hasSize(2);
    }
}
