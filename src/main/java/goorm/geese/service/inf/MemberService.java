package goorm.geese.service.inf;

import goorm.geese.domain.entity.Member;
import goorm.geese.dto.auth.*;
import goorm.geese.jwt.JwtToken;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    JwtToken login(LoginRequestDto request);
    JwtToken reissue(ReissueRequestDto request);
    void signup(SignUpRequestDto request);
    void sendCodeToEmail(String toEmail);

    void checkDuplicatedNickname(VerifiedNicknameRequest verifiedRequestDto);

    void verifiedCode(VerifiedRequestDto verifiedRequestDto);

    void logout(String email);

    Member findMemberByNickname(String username);

    boolean isProfileComplete(String email);

    void completeSocialRegistration(Member member, SignUpRequestDto requestDto);
}