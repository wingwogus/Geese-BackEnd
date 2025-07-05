package goorm.geese.controller;

import goorm.geese.domain.entity.Card;
import goorm.geese.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // 카드 3개의 keyword만 반환
    @GetMapping("")
    public ResponseEntity<List<String>> getKeywords() {
        List<String> keywords = cardService.getRandomKeywords();
        return ResponseEntity.ok(keywords);
    }

    // 선택된 keyword에 해당하는 카드 전체 정보 반환
    @GetMapping("/detail")
    public ResponseEntity<Card> getCardByKeyword(@RequestParam String keyword) {
        Card card = cardService.getCardByKeyword(keyword);

        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(card);
    }
}
