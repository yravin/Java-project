package com.learnapi.test.service.impl;
import com.learnapi.test.dto.MemberRequest;
import com.learnapi.test.dto.MemberResponse;
import com.learnapi.test.model.Member;
import com.learnapi.test.repository.MemberRepository;
import com.learnapi.test.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberServiceImpl  implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void create(MemberRequest memberRequest) {
        log.info("Create Member ");
        Member member = new Member();
        //memberRequest.getName() (GET): ទាញយកឈ្មោះដែល User បានបំពេញពី Form បង្កើតគណនីថ្មី
        //member.setName(...) (SET): យកឈ្មោះនោះទៅដាក់ចូលក្នុងប្រអប់ទទេថ្មី (member)
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        memberRepository.save(member);
    }

    @Override
    public void update(Long id ,MemberRequest memberRequest) {
        log.info("update Member");
        Member member = memberRepository.findById(id)
         .orElseThrow(() ->
                new RuntimeException("Book not found: " + id));
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        memberRepository.save(member);
    }


    @Override
    public void delete(Long id) {
      log.info("delete Member");
      memberRepository.deleteById(id);
    }

    @Override
    public MemberResponse getById(Long id) {
             Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(member.getId());
        memberResponse.setEmail(member.getEmail());
        memberResponse.setName(member.getName());
        return memberResponse;
    }

    @Override
    public List<MemberResponse> getAll() {
       List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    private MemberResponse mapToResponse(Member member){
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(Long.valueOf(member.getId()));
        memberResponse.setEmail(member.getEmail());
        memberResponse.setName(member.getName());
        return memberResponse;
    }
}
