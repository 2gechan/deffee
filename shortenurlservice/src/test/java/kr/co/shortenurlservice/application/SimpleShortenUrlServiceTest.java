package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.NotFoundShortenUrlException;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SimpleShortenUrlServiceTest {

    @Autowired
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("URL을 단축한 후 단축된 URL 키로 조회하면 원래 URL이 조회되어야 한다.")
    public void shortenUrlAddTest() throws Exception {
        // given
        String expectedOriginalUrl = "http://www.hanbit.co.kr";
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(expectedOriginalUrl);

        // when
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
        String shortenUrlKey = shortenUrlCreateResponseDto.getShortenUrlKey();
        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);

        // then
        assertTrue(originalUrl.equals(expectedOriginalUrl));

    }

    @Test
    @DisplayName("등록되지 않는 shortenUrl 로 접근 시 NotFoundShortenUrlException 발생한다.")
    public void NotFoundShortenUrlExceptionTest() throws Exception {
        // given
        String shortenUrlKey = "abcdefg";

        // when & then
        NotFoundShortenUrlException notFoundShortenUrlException = assertThrows(NotFoundShortenUrlException.class, () -> {
            simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        });

    }

}