package goorm.geese.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Device {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "device")
    @Builder.Default
    private List<DeviceComponent> deviceComponents = new ArrayList<>();

    @OneToMany(mappedBy = "device")
    private List<Post> posts = new ArrayList<>();
}
