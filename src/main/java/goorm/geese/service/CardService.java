package goorm.geese.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import goorm.geese.domain.entity.Card;
import goorm.geese.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    // CardData.json 파일에서 카드 전체 불러오기
    private List<Card> loadCards() {
        return cardRepository.findAll();

//        try {
//            InputStream input = getClass().getClassLoader().getResourceAsStream("CardData.json");
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readValue(input, new TypeReference<>() {});
//        } catch (Exception e) {
//            throw new RuntimeException("카드를 불러오는 중 오류가 발생했습니다.");
//        }
    }

    // 카드 3개의 keyword만 반환
    public List<String> getRandomKeywords() {
        List<Card> cards = loadCards();
        Collections.shuffle(cards); // 카드 섞기
        return cards.stream()
                .limit(3)
                .map(Card::getKeyword) // keyword만 뽑기
                .collect(Collectors.toList());
    }

    // 선택된 keyword에 해당하는 카드 정보 반환
    public Card getCardByKeyword(String keyword) {
        return loadCards().stream()
                .filter(card -> card.getKeyword().equals(keyword))
                .findFirst()
                .orElse(null); // 없을 경우 null 반환
    }
}
