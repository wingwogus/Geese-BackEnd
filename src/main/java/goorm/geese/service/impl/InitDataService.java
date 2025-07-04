package goorm.geese.service.impl;

import goorm.geese.domain.entity.Device;
import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Role;
import goorm.geese.repository.DeviceRepository;
import goorm.geese.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataService {

    private final MemberRepository memberRepository;
    private final DeviceRepository deviceRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init() {
        Member member = Member.builder()
                .username("akzmfpqk@naver.com")
                .password(passwordEncoder.encode("1234"))
                .nickname("아 진상")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        Device device1 = Device.builder()
                .name("갤럭시 S25")
                .build();

        deviceRepository.save(device1);
    }
}
