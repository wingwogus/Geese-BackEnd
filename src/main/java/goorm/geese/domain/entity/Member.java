package goorm.geese.domain.entity;

import jakarta.persistence.*;

import lombok.*;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false, updatable = false)
    private Long id;

    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
}
