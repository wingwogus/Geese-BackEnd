package goorm.geese.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FeatureType {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private FeatureCategory category; // ex. PROCESSOR, RAM, CAMERA_FRONT 등

    private String name;

    @OneToMany(mappedBy = "featureType")
    private List<Component> components = new ArrayList<>();
}
