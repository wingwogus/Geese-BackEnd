package goorm.geese.dto.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchRequest {
    private LocalDateTime from;
    private LocalDateTime to;
}
