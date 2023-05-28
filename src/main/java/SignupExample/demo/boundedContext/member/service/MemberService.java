package SignupExample.demo.boundedContext.member.service;

import SignupExample.demo.boundedContext.member.entity.Member;
import SignupExample.demo.boundedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public Member join(String username, String password) {
        Member member = Member.builder()
                .username(username)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .password(passwordEncoder.encode(password))
                .build();

        return memberRepository.save(member);
    }

}
