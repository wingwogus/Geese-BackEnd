package goorm.geese.service;

import goorm.geese.domain.entity.Member;
import goorm.geese.domain.entity.Role;
import goorm.geese.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init() {
        Member member = Member.builder()
                .email("akzmfpqk@naver.com")
                .password(passwordEncoder.encode("1234"))
                .nickname("하이룰ㅇ")
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }
}
