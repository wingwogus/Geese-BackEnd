package goorm.geese.controller;

import goorm.geese.domain.entity.Card;
import goorm.geese.dto.ApiResponse;
import goorm.geese.service.impl.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Comment", description = "카드뽑기 API")
@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @Operation(summary = "카드 키워드 반환", description = "랜덤 카드 3개의 키워드를 반환합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<String>>> getKeywords() {
        List<String> keywords = cardService.getRandomKeywords();
        return ResponseEntity.ok(ApiResponse.success("카드 키워드 반환 완료", keywords));
    }

    @Operation(summary = "키워드 기반 카드 반환", description = "키워드를 기준으로 카드를 반환합니다.")
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<Card>> getCardByKeyword(@RequestParam String keyword) {
        Card card = cardService.getCardByKeyword(keyword);

        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ApiResponse.success("카드 반환 완료", card));
    }
}
