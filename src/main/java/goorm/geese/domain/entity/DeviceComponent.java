package goorm.geese.domain.entity;

import jakarta.persistence.*;

@Entity
public class DeviceComponent {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    private Double score;
}