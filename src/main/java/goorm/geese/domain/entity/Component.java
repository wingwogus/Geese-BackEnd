package goorm.geese.domain.entity;

import jakarta.persistence.*;

@Entity
public class Component {
    @Id
    @GeneratedValue
    private Long id;

    private String name; // ex: Snapdragon 8 Gen 3
    private String manufacturer; // ex: Qualcomm

    @ManyToOne
    @JoinColumn(name = "feature_type_id")
    private FeatureType featureType;

    // 필요시 클럭, 코어 수, 공정 등 추가
}
