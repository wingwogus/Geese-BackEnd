package goorm.geese.domain.entity;

import jakarta.persistence.*;

@Entity
public class DeviceFeature {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "feature_type_id")
    private FeatureType featureType;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    private Double score; // 해당 기능에 대한 점수
}