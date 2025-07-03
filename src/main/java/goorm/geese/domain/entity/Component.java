package goorm.geese.domain.entity;

import jakarta.persistence.*;

@Entity
public class Component {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private FeatureCategory category;

    private String name; // ex: Snapdragon 8 Gen 3

    private String manufacturer; // ex: Qualcomm
}
