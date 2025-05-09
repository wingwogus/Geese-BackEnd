package goorm.geese.controller;

import goorm.geese.domain.dto.EmailRequest;
import goorm.geese.domain.dto.SignUpRequest;
import goorm.geese.domain.dto.SingleResponseDto;
import goorm.geese.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/emails/signup")
    public ResponseEntity sendMessage(@RequestBody EmailRequest emailRequest) {
        memberService.sendCodeToEmail(emailRequest.getEmail());

        return new ResponseEntity<>("이메일을 전송했습니다",HttpStatus.OK);
    }

    @PostMapping("/emails/verification")
    public ResponseEntity verificationEmail(@RequestBody SignUpRequest signUpRequest) {

        boolean verified =
                memberService.verifiedCode(signUpRequest.getEmail(), signUpRequest.getCode());

        if (!verified) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memberService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}