package store.bizscanner.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.request.ScrapRequest;
import store.bizscanner.dto.response.scrap.ScrapResponse;
import store.bizscanner.entity.Member;
import store.bizscanner.entity.Scrap;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ScrapServiceTest {
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
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member);

        // then
        assertThat(scrapService.findScrap(scrapRequest, member)).isEqualTo(savedScrap);
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
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member);
        scrapService.deleteScrap(scrapRequest, member);

        // then
        assertThatThrownBy(() -> scrapService.findScrap(scrapRequest, member))
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
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member);

        // then
        assertThat(scrapService.isScrapped(careaCode, jcategoryCode, member)).isTrue();
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
        Scrap savedScrap = scrapService.createScrap(scrapRequest, member);
        Scrap savedScrap2 = scrapService.createScrap(scrapRequest2, member);

        // then
        List<ScrapResponse> scrapResponses = scrapService.getScrapResponses(member);

        assertThat(scrapResponses.get(0).getCareaCode()).isEqualTo(savedScrap.getCareaCode());
        assertThat(scrapResponses.get(1).getCareaCode()).isEqualTo(savedScrap2.getCareaCode());
        assertThat(scrapResponses.size()).isEqualTo(2);
    }
}
